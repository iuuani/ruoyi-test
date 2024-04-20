import { $on, $off, $once, $emit } from '../gogocodeTransfer'
import Dict from './Dict'
import { mergeOptions } from './DictOptions'

export default function (Vue, options) {
  mergeOptions(options)
  window.$vueApp.mixin({
    data() {
      if (
        this.$options === undefined ||
        this.$options.dicts === undefined ||
        this.$options.dicts === null
      ) {
        return {}
      }
      const dict = new Dict()
      dict.owner = this
      return {
        dict,
      }
    },
    created() {
      if (!(this.dict instanceof Dict)) {
        return
      }
      options.onCreated && options.onCreated(this.dict)
      this.dict.init(this.$options.dicts).then(() => {
        options.onReady && options.onReady(this.dict)
        this.$nextTick(() => {
          $emit(this, 'dictReady', this.dict)
          if (
            this.$options.methods &&
            this.$options.methods.onDictReady instanceof Function
          ) {
            this.$options.methods.onDictReady.call(this, this.dict)
          }
        })
      })
    },
  })
}
