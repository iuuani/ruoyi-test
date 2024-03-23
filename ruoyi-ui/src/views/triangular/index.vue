<template>
  <div>
    <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
      <el-form-item label="边a" prop="field101">
        <el-input v-model="formData.field101" placeholder="请输入边a" clearable :style="{width: '100%'}">
        </el-input>
      </el-form-item>
      <el-form-item label="边b" prop="field102">
        <el-input v-model="formData.field102" placeholder="请输入边b" clearable :style="{width: '100%'}">
        </el-input>
      </el-form-item>
      <el-form-item label="边c" prop="field103">
        <el-input v-model="formData.field103" placeholder="请输入边c" clearable :style="{width: '100%'}">
        </el-input>
      </el-form-item>
      <!-- <el-form-item label="三角形类型" prop="field105">
        <el-input v-model="formData.field105" readonly :disabled='true' clearable :style="{width: '100%'}">
        </el-input>
      </el-form-item>
      <el-form-item size="large">
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form-item> -->
      <el-form-item size="large">
        <el-button type="primary" @click="calculateTriangleType()">提交</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form-item>
      <el-form-item label="计算结果" prop="field105">
        <el-input v-model="formData.field105" readonly clearable :style="{width: '100%'}">
        </el-input>
      </el-form-item>
    </el-form>

    <div style="display: grid; justify-content: center;">
    <!-- 用于绘制三角形的 Canvas 元素 -->
    <canvas ref="canvas" width="1200" height="400" style="border:1px solid black;"></canvas>
    </div>
  </div>
</template>
<script>
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
      },
      rules: {
        field101: [{
          required: true,
          message: '请输入边a',
          trigger: 'blur'
        }],
        field102: [{
          required: true,
          message: '请输入边b',
          trigger: 'blur'
        }],
        field103: [{
          required: true,
          message: '请输入边c',
          trigger: 'blur'
        }],
        field105: [{
          required: true,
          message: '',
          trigger: 'blur'
        }],
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
        if (!valid) return
        // TODO 提交表单
        this.calculateTriangleType;
      })
    },
    resetForm() {
      this.$refs['elForm'].resetFields()
      // 清除 Canvas 中的内容
      const canvas = this.$refs.canvas;
      const ctx = canvas.getContext('2d');
      ctx.clearRect(0, 0, canvas.width, canvas.height);
    },
    // 计算三角形类型
    calculateTriangleType() {
      const a = parseFloat(this.formData.field101);
      const b = parseFloat(this.formData.field102);
      const c = parseFloat(this.formData.field103);
      const A2 = a ** 2;
      const B2 = b ** 2;
      const C2 = c ** 2;
      const t = (A2 + B2 - C2) / (2 * a * b);
      const m = Math.sqrt(1 - t ** 2);
      const h = b * m;
      const x = Math.sqrt(B2 - h ** 2);

      // 判断是否可以构成三角形
      if (a + b > c && a + c > b && b + c > a) {
        // 判断三角形类型
        if (a === b && b === c) {
          this.formData.field105 = '等边三角形';
        } else if (a === b || b === c || a === c) {
          this.formData.field105 = '等腰三角形';
        } else if (a * a + b * b === c * c || a * a + c * c === b * b || b * b + c * c === a * a) {
          this.formData.field105 = '直角三角形';
        } else {
          this.formData.field105 = '一般三角形';
        }

        // 获取 Canvas 元素和上下文
        const canvas = this.$refs.canvas;
        const ctx = canvas.getContext('2d');

        // 清除之前绘制的内容
        ctx.clearRect(0, 0, canvas.width, canvas.height);

        // 绘制三角形
        ctx.beginPath();
        ctx.moveTo(100, 300);
        ctx.lineTo(100+a, 300);
        ctx.lineTo(100+x, 300-h);
        ctx.closePath();
        ctx.stroke();
      } else {
        this.formData.field105 = '无法构成三角形';
      }
    }
  }
}
</script>

<style>
</style>