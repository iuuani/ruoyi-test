<script>
import * as Vue from 'vue'
export default function render(_props, _context) {
  const context = {
    ..._context,
    props: _props,
    data: _context.attr,
    children: _context.slots,
  }
  const { icon, title } = context.props
  const vnodes = []

  if (icon) {
    vnodes.push(<svg-icon icon-class={icon} />)
  }

  if (title) {
    if (title.length > 5) {
      vnodes.push(
        <span slot="title" title={title}>
          {title}
        </span>
      )
    } else {
      vnodes.push(<span slot="title">{title}</span>)
    }
  }
  return vnodes
}
</script>
