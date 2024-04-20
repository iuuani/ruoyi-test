<template>
  <el-container>
    <el-header>
      <el-input v-model:value="input" placeholder="请输入内容"></el-input>
    </el-header>
    <el-main>
      <el-table :data="tableData.value" style="width: 100%">
        <el-table-column prop="date" label="日期" width="180">
        </el-table-column>
        <el-table-column prop="name" label="姓名" width="180">
        </el-table-column>
        <el-table-column prop="address" label="地址"> </el-table-column>
      </el-table>
    </el-main>
  </el-container>
</template>

<script>
export default {
  name: 'list',
}
</script>

<script setup>
import ElementUI from 'element-ui'
import { ref } from 'vue'
const tableData =
  ref[
    ({
      date: '2016-05-02',
      name: '王小虎',
      address: '上海市普陀区金沙江路 1518 弄',
    },
    {
      date: '2016-05-04',
      name: '王小虎',
      address: '上海市普陀区金沙江路 1517 弄',
    })
  ]
</script>

<style lang="scss" scoped></style>
