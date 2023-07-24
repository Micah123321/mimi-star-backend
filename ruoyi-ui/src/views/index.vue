<template>
  <div class="app-container home">
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <h2>关于项目</h2>
          </div>
          <p>m-ruoyi-vue快速开发平台</p>
          <p>
            m-ruoyi-vue快速开发平台基于开源项目“RuoYi-Vue”优化改造，追求快速开发，一切向效率看齐，重构优化前后端的代码。平台将持续升级，持续完善。欢迎star和fork。
          </p>
          <p>
            <b>当前版本:</b> <span>v{{ version }}</span>
          </p>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="16">
        <el-card class="update-log">
          <panel-group @handleSetLineChartData="handleSetLineChartData" />
        </el-card>
      </el-col>
    </el-row>
    <br/>
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <h2>更新日志</h2>
          </div>
          <el-collapse accordion>
            <el-collapse-item title="v1.0.0 - 2023-07-23">
              <ol>
                <li>快速开发平台正式发布</li>
              </ol>
            </el-collapse-item>
          </el-collapse>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="16">
        <el-card class="update-log">
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import PanelGroup from './dashboard/PanelGroup'

export default {
  name: "Index",
  components: {
    PanelGroup
  },
  data() {
    return {
      // 版本号
      version: "1.0.0",
    };
  },
  methods: {
    goTarget(href) {
      window.open(href, "_blank");
    },
    handleSetLineChartData(type) {
      this.lineChartData = lineChartData[type]
    }
  },
};
</script>

<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }
  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }
  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 5px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}
</style>

