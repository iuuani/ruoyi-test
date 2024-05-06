<template>
  <div>
    <el-row :gutter="20">
      <el-col :offset="6" :span="12">
        <h4>
      要求输入3个介于1-100之间的正数a、b、c作为三角形的三条边，判断这3个数构成的是一般三角形、等边三角形、等腰三角形、直角三角形，还是无法构成三角形。
        </h4>
      </el-col>
    </el-row>
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
            <el-input v-model="formData.data.type" readonly clearable :style="{width: '100%'}"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="7">
          <el-form-item label="三角形面积" prop="field105">
            <el-input v-model="formData.data.area" readonly clearable :style="{width: '100%'}"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="7">
          <el-form-item label="三角形周长" prop="field106">
            <el-input v-model="formData.data.circumference" readonly clearable :style="{width: '100%'}"></el-input>
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
import request from '@/utils/request';

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
        data: {
          area: undefined,
          circumference: undefined,
          type: undefined
        }
      },
      rules: {
        field101: [{
          required: true,
          message: '请输入三角形边a',
          trigger: 'blur'
        }],
        field102: [{
          required: true,
          message: '请输入三角形边b',
          trigger: 'blur'
        }],
        field103: [{
          required: true,
          message: '请输入三角形边c',
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
        const data = {
          sideA: parseFloat(this.formData.field101),
          sideB: parseFloat(this.formData.field102),
          sideC: parseFloat(this.formData.field103)
        };
        // 发送 POST 请求
        this.postMessage(data);
      });
    },
    postMessage(data) {
      request({
        url: '/triangular',
        method: 'post',
        data: data
      }).then(response => {
        this.formData.data.area = response.data.area;
        this.formData.data.circumference = response.data.circumference;
        this.formData.data.type = response.data.type;
        if (response.data.type !== "无法构成三角形" && response.data.type !== "无效三角形") {
          this.drawTriangles();
        } else {
          const canvas = this.$refs.canvas;
          const ctx = canvas.getContext('2d');
          ctx.clearRect(0, 0, canvas.width, canvas.height);
        }
      }).catch(error => {
        console.error('Error:', error);
      });
    },
    resetForm() {
      this.$refs['elForm'].resetFields()
    },
    drawTriangles() {
      // 获取 Canvas 元素和上下文
      const canvas = this.$refs.canvas;
      const ctx = canvas.getContext('2d');
      let a = parseFloat(this.formData.field101);
      let b = parseFloat(this.formData.field102);
      let c = parseFloat(this.formData.field103);
      let x1 =a;
      if(a<b){
        x1=a;
        a=b;
        b=x1;
      }

      if(a<c){
        x1 = a;
        a=c;
        c=x1;
      }

      console.log("a=",a);
      let ratio = 300 /a;
      a = a * ratio;
      b= b* ratio;
      c = c*ratio;
      const A2 = a ** 2;
      const B2 = b ** 2;
      const C2 = c ** 2;
      const t = (A2 + B2 - C2) / (2 * a * b);
      const m = Math.sqrt(1 - t ** 2);
      const h = b * m;
      const x = Math.sqrt(B2 - h ** 2);



      // 清除之前绘制的内容
      ctx.clearRect(0, 0, canvas.width, canvas.height);

      ctx.beginPath();
      ctx.moveTo(100, 300);
      ctx.lineTo(100+a, 300);
      ctx.lineTo(100+x, 300-h);
      ctx.closePath();
      ctx.stroke();
    }
  }
}
</script>

<style>
</style>
