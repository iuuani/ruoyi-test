<template>
  <div>
    <el-row :gutter="15">
      <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
        <el-col :span="24">
          <el-form-item label="边A" prop="field101">
            <el-input v-model="formData.field101" placeholder="请输入三角形边A" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="边B" prop="field102">
            <el-input v-model="formData.field102" placeholder="请输入三角形边B" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="边C" prop="field103">
            <el-input v-model="formData.field103" placeholder="请输入三角形边C" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="7">
          <el-form-item label="三角形类型" prop="field104">
            <el-input v-model="formData.field104" readonly :disabled='true' clearable
              :style="{width: '100%'}"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="7">
          <el-form-item label="三角形面积" prop="field105">
            <el-input v-model="formData.field105" readonly :disabled='true' clearable
              :style="{width: '100%'}"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="7">
          <el-form-item label="三角形周长" prop="field106">
            <el-input v-model="formData.field106" readonly :disabled='true' clearable
              :style="{width: '100%'}"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item size="large">
            <el-button type="primary" @click="submitForm">提交</el-button>
            <el-button @click="resetForm">重置</el-button>
          </el-form-item>
        </el-col>
      </el-form>
    </el-row>
    <div style="display: grid; justify-content: center;">
      <canvas ref="canvas" width="1200" height="400" style="border:1px solid black;"></canvas>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  components: {},
  props: [],
  data() {
    return {
      formData: {
        field101: undefined,
        field102: undefined,
        field103: undefined,
        field104: undefined,
        field105: undefined,
        field106: undefined,
      },
      rules: {
        field101: [{
          required: true,
          message: '请输入三角形边A',
          trigger: 'blur'
        }],
        field102: [{
          required: true,
          message: '请输入三角形边B',
          trigger: 'blur'
        }],
        field103: [{
          required: true,
          message: '请输入三角形边C',
          trigger: 'blur'
        }],
        field104: [],
        field105: [],
        field106: [],
      },
    }
  },
  computed: {},
  watch: {},
  created() {},
  mounted() {},
  methods: {
    submitForm() {
      this.$refs['elForm'].validate(valid => {
        if (!valid) return;
        // 构造请求参数
        const requestData = {
          sideA: parseFloat(this.formData.field101),
          sideB: parseFloat(this.formData.field102),
          sideC: parseFloat(this.formData.field103)
        };
        // 发送 POST 请求
        axios.post('/triangular', requestData)
          .then(response => {
            // 请求成功的处理逻辑
            console.log('请求成功:', response.data);
          })
          .catch(error => {
            // 请求失败的处理逻辑
            console.error('请求失败:', error);
          });
      });
    },
    resetForm() {
      this.$refs['elForm'].resetFields()
    },
  }
}

</script>
<style>
</style>
