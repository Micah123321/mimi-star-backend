<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="班级名称" prop="classesName">
        <el-input
          v-model="queryParams.classesName"
          placeholder="请输入班级名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="班级年级" prop="classesGrade">
        <el-input
          v-model="queryParams.classesGrade"
          placeholder="请输入班级年级"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="班主任" prop="classesTeacher">
        <el-input
          v-model="queryParams.classesTeacher"
          placeholder="请输入班主任"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['demo:classes:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['demo:classes:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['demo:classes:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['demo:classes:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="classesList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="classesId" />
      <el-table-column label="班级名称" align="center" prop="classesName" />
      <el-table-column label="班级年级" align="center" prop="classesGrade" />
      <el-table-column label="班主任" align="center" prop="classesTeacher" />
      <el-table-column label="状态" align="center" prop="status" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['demo:classes:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['demo:classes:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改主子对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="班级名称" prop="classesName">
          <el-input v-model="form.classesName" placeholder="请输入班级名称" />
        </el-form-item>
        <el-form-item label="班级年级" prop="classesGrade">
          <el-input v-model="form.classesGrade" placeholder="请输入班级年级" />
        </el-form-item>
        <el-form-item label="班主任" prop="classesTeacher">
          <el-input v-model="form.classesTeacher" placeholder="请输入班主任" />
        </el-form-item>
        <el-form-item label="删除标志" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入删除标志" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-divider content-position="center">单信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddDemoStudent">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteDemoStudent">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="demoStudentList" :row-class-name="rowDemoStudentIndex" @selection-change="handleDemoStudentSelectionChange" ref="demoStudent">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="学生名称" prop="studentName" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.studentName" placeholder="请输入学生名称" />
            </template>
          </el-table-column>
          <el-table-column label="年龄" prop="studentAge" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.studentAge" placeholder="请输入年龄" />
            </template>
          </el-table-column>
          <el-table-column label="性别" prop="studentSex" width="150">
            <template slot-scope="scope">
              <el-select v-model="scope.row.studentSex" placeholder="请选择性别">
                <el-option
                  v-for="dict in dict.type.sys_user_sex"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="生日" prop="studentBirthday" width="240">
            <template slot-scope="scope">
              <el-date-picker clearable v-model="scope.row.studentBirthday" type="date" value-format="yyyy-MM-dd" placeholder="请选择生日" />
            </template>
          </el-table-column>
          <el-table-column label="状态" prop="STATUS" width="150">
            <template slot-scope="scope">
              <el-select v-model="scope.row.STATUS" placeholder="请选择状态">
                <el-option
                  v-for="dict in dict.type.sys_common_status"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listClasses, getClasses, delClasses, addClasses, updateClasses } from "@/api/demo/classes";

export default {
  name: "Classes",
  dicts: ['sys_common_status', 'sys_user_sex'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedDemoStudent: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 主子表格数据
      classesList: [],
      // 单表格数据
      demoStudentList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        classesName: null,
        classesGrade: null,
        classesTeacher: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询主子列表 */
    getList() {
      this.loading = true;
      listClasses(this.queryParams).then(response => {
        this.classesList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        classesId: null,
        classesName: null,
        classesGrade: null,
        classesTeacher: null,
        status: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        delFlag: null,
        remark: null
      };
      this.demoStudentList = [];
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.classesId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加主子";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const classesId = row.classesId || this.ids
      getClasses(classesId).then(response => {
        this.form = response.data;
        this.demoStudentList = response.data.demoStudentList;
        this.open = true;
        this.title = "修改主子";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.demoStudentList = this.demoStudentList;
          if (this.form.classesId != null) {
            updateClasses(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addClasses(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const classesIds = row.classesId || this.ids;
      this.$modal.confirm('是否确认删除主子编号为"' + classesIds + '"的数据项？').then(function() {
        return delClasses(classesIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** 单序号 */
    rowDemoStudentIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 单添加按钮操作 */
    handleAddDemoStudent() {
      let obj = {};
      obj.studentName = "";
      obj.studentAge = "";
      obj.studentSex = "";
      obj.studentBirthday = "";
      obj.STATUS = "";
      obj.remark = "";
      this.demoStudentList.push(obj);
    },
    /** 单删除按钮操作 */
    handleDeleteDemoStudent() {
      if (this.checkedDemoStudent.length == 0) {
        this.$modal.msgError("请先选择要删除的单数据");
      } else {
        const demoStudentList = this.demoStudentList;
        const checkedDemoStudent = this.checkedDemoStudent;
        this.demoStudentList = demoStudentList.filter(function(item) {
          return checkedDemoStudent.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleDemoStudentSelectionChange(selection) {
      this.checkedDemoStudent = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('demo/classes/export', {
        ...this.queryParams
      }, `classes_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
