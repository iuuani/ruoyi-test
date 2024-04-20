<template>
  <div>
    <el-dialog
      v-bind="$attrs"
      :close-on-click-modal="false"
      :modal-append-to-body="false"
      @open="onOpen"
      @close="onClose"
    >
      <el-row :gutter="0">
        <el-form
          ref="elForm"
          :model="formData"
          :rules="rules"
          size="small"
          label-width="100px"
        >
          <el-col :span="24">
            <el-form-item label="选项名" prop="label">
              <el-input
                v-model:value="formData.label"
                placeholder="请输入选项名"
                clearable
              />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="选项值" prop="value">
              <el-input
                v-model:value="formData.value"
                placeholder="请输入选项值"
                clearable
              >
                <template v-slot:append>
                  <el-select
                    v-model:value="dataType"
                    :style="{ width: '100px' }"
                  >
                    <el-option
                      v-for="(item, index) in dataTypeOptions"
                      :key="index"
                      :label="item.label"
                      :value="item.value"
                      :disabled="item.disabled"
                    />
                  </el-select>
                </template>
              </el-input>
            </el-form-item>
          </el-col>
        </el-form>
      </el-row>
      <template v-slot:footer>
        <div>
          <el-button type="primary" @click="handleConfirm"> 确定 </el-button>
          <el-button @click="close"> 取消 </el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import { $on, $off, $once, $emit } from '../../../utils/gogocodeTransfer'
import { isNumberStr } from '@/utils/index'

function inheriltClassAndStyle() {
  const attrs = this.$attrs
  attrs.class && this.$el.classList.add(attrs.class)
  attrs.style &&
    Object.entries(attrs.style).forEach(([k, v]) => {
      this.$el.style[k] = v
    })
}
export default {
  components: {},
  inheritAttrs: false,
  props: [],
  data() {
    return {
      id: 100,
      formData: {
        label: undefined,
        value: undefined,
      },
      rules: {
        label: [
          {
            required: true,
            message: '请输入选项名',
            trigger: 'blur',
          },
        ],
        value: [
          {
            required: true,
            message: '请输入选项值',
            trigger: 'blur',
          },
        ],
      },
      dataType: 'string',
      dataTypeOptions: [
        {
          label: '字符串',
          value: 'string',
        },
        {
          label: '数字',
          value: 'number',
        },
      ],
    }
  },
  computed: {},
  watch: {
    // eslint-disable-next-line func-names
    'formData.value': function (val) {
      this.dataType = isNumberStr(val) ? 'number' : 'string'
    },
  },
  created() {},
  methods: {
    onOpen() {
      this.formData = {
        label: undefined,
        value: undefined,
      }
    },
    onClose() {},
    close() {
      $emit(this, 'update:visible', false)
    },
    handleConfirm() {
      this.$refs.elForm.validate((valid) => {
        if (!valid) return
        if (this.dataType === 'number') {
          this.formData.value = parseFloat(this.formData.value)
        }
        this.formData.id = this.id++
        $emit(this, 'commit', this.formData)
        this.close()
      })
    },
  },
  mounted() {
    inheriltClassAndStyle.call(this)
  },
  emits: ['update:visible', 'commit'],
}
</script>
