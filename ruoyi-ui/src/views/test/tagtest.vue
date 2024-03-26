<template>
  <div class="container">
    <div class="div1">
      <!-- div1 的内容 -->
      <el-tag
        v-for="div1Tag in div1Tags"
        :key="div1Tag.name"
        @click="removeTagFromDiv1(div1Tag)"
        class="tag green-tag">
        {{div1Tag.name}}
      </el-tag>
    </div>
    <div class="div2" style="position: relative;">
      <!-- div2 的内容 -->
      <el-tag
        v-for="div2Tag in div2Tags"
        :key="div2Tag.name"
        @click="addTagToDiv1(div2Tag)"
        :class="{ 'tag': true, 'green-tag': div2Tag.style === 'green-tag', 'grey-tag': div2Tag.style === 'grey-tag' }"
        >
        {{div2Tag.name}}
      </el-tag>
      <el-button type="primary" size="mini" style="position: absolute; bottom: 0; left: 0; width: 60px;" @click="addNewHobby">新增</el-button>
      <el-input v-model="input" placeholder="爱好" size="mini" style="position: absolute; bottom: 0; left: 60px; width: 60px;"></el-input>
      <el-button type="text" size="medium" style="position: absolute; bottom: 0; right: 0; width: 60px; color: red;" @click="removeSelectedHobbies()">删除</el-button>
    </div>
  </div>
</template>

<script>
import { listHobbies, getHobbies, delHobbies, addHobbies, updateHobbies } from "@/api/test/hobbies";

export default {
  data() {
    return {
      div1Tags: [],
      div2Tags: [],
      input: '',
      hobbiesNames: [],
      hobbiesMap: new Map()
    };
  },
  created() {
    this.getList();
  },
  methods: {
    getList() {
      this.loading = true;
      listHobbies(this.queryParams).then(response => {
        response.rows.forEach(hobby => {
          this.hobbiesMap.set(hobby.id, hobby.hobby);
        });
        console.log(this.hobbiesMap);
        this.div2Tags = response.rows.map(hobby => ({
          name: hobby.hobby,
          style: 'grey-tag'
        }));
      });
    },
    addTagToDiv1(div2Tag) {
      const index = this.div1Tags.findIndex(t => t.name === div2Tag.name);
      if (index === -1) {
        // 将标签复制到 div1
        this.div1Tags.push(div2Tag);
        // 修改 div2 中标签的样式为 div1 的样式
        const div2Index = this.div2Tags.findIndex(t => t.name === div2Tag.name);
        if (div2Index !== -1) {
          this.$set(this.div2Tags[div2Index], 'style', 'green-tag');
        }
      } else {
        // 如果已经选中了，则从 div1 中删除该标签，并将 div2 中的样式改为灰色
        this.div1Tags.splice(index, 1);
        const div2Index = this.div2Tags.findIndex(t => t.name === div2Tag.name);
        if (div2Index !== -1) {
          this.$set(this.div2Tags[div2Index], 'style', 'grey-tag');
        }
      }
    },
    removeTagFromDiv1(div1Tag) {
      const index = this.div1Tags.findIndex(t => t.name === div1Tag.name);
      if (index !== -1) {
        this.div1Tags.splice(index, 1);
        // 将对应的 div2Tag 标签样式改为灰色
        const div2Index = this.div2Tags.findIndex(t => t.name === div1Tag.name);
        if (div2Index !== -1) {
          this.$set(this.div2Tags[div2Index], 'style', 'grey-tag');
        }
      }
    },
    // 新增爱好按钮点击事件
    addNewHobby() {
      if (this.input.trim() === '') {
        // 输入框为空时，不进行任何操作
        return;
      }

      if (this.hobbiesNames.includes(this.input.trim())) {
        // 如果输入的爱好已经存在于hobbiesNames数组中
        this.$message.warning('该爱好已存在');
        this.input = '';
      } else {
        // 调用addHobbies方法添加新的学生爱好
        addHobbies({ hobby: this.input.trim() }).then(() => {
          this.$message.success('增加成功');
          // 将新爱好放入div2中
          this.div2Tags.push({ name: this.input.trim(), style: 'grey-tag' });
          // 清空输入框
          this.input = '';
        }).catch(error => {
          // console.error('添加爱好失败', error);
          this.$message.error('添加爱好失败');
        });
      }
    },
    // 删除选中标签的方法
    removeSelectedHobbies() {
      const selectedValues = this.div1Tags.map(tag => tag.name);
      const selectedIds = [];
      for (const [id, value] of this.hobbiesMap.entries()) {
        if (selectedValues.includes(value)) {
          selectedIds.push(id);
        }
      }
      console.log('要删除的爱好 ID 数组:', selectedIds);
      selectedIds.forEach(id => {
        delHobbies(id)
          .then(() => {
            console.log(`成功删除 ID 为 ${id} 的爱好`);
            // 从 div1Tags 中移除对应的标签
            const index = this.div1Tags.findIndex(tag => tag.id === id);
            if (index !== -1) {
              this.div1Tags.splice(index, 1);
            }
            // 从 div2Tags 中移除对应的标签
            const index2 = this.div2Tags.findIndex(tag => tag.id === id);
            if (index2 !== -1) {
              this.div2Tags.splice(index2, 1);
            }
            this.$nextTick(() => {
              // 在这里执行其他操作
            });
          })
          .catch(error => {
            console.error(`删除 ID 为 ${id} 的爱好失败:`, error);
          });
      });
    }


  }
};
</script>

<style scoped>
.container {
  position: relative;
}

.div1 {
  width: 237px;
  height: 33px;
  border: 2px solid black;
}

.div2 {
  position: relative;
  width: 237px;
  height: 150px;
  border: 2px solid black;
}

.el-tag {
  display: inline-block;
  margin-right: 1px;
}

.tag {
  color: white;
}

.green-tag {
  background-color:darkseagreen;
}

.grey-tag {
  background-color:grey;
}

</style>
