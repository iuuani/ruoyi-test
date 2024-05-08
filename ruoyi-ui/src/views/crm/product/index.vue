<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="产品编码" prop="prodCode">
        <el-input
          v-model="queryParams.prodCode"
          placeholder="请输入产品编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产品名称" prop="prodName">
        <el-input
          v-model="queryParams.prodName"
          placeholder="请输入产品名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker
          v-model="daterangeGmtCreate"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="修改时间">
        <el-date-picker
          v-model="daterangeGmtModified"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
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
          v-hasPermi="['crm:product:add']"
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
          v-hasPermi="['crm:product:edit']"
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
          v-hasPermi="['crm:product:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['crm:product:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="productList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="产品编号" align="center" prop="id" />
      <el-table-column label="产品编码" align="center" prop="prodCode" />
      <el-table-column label="属性编号" align="center" prop="prodAttr" />
      <el-table-column label="类型编号" align="center" prop="prodType" />
      <el-table-column label="产品名称" align="center" prop="prodName" />
      <el-table-column label="品牌编号" align="center" prop="prodBrand" />
      <el-table-column label="规格型号" align="center" prop="prodSpecs" />
      <el-table-column label="单位编号" align="center" prop="prodUnit" />
      <el-table-column label="成本单价" align="center" prop="costPrice" />
      <el-table-column label="增值税税率" align="center" prop="vatRate" />
      <el-table-column label="销售单价(含税)" align="center" prop="inclTaxPrice" />
      <el-table-column label="销售单价(不含税)" align="center" prop="exclTaxPrice" />
      <el-table-column label="税额" align="center" prop="taxAmount" />
      <el-table-column label="售价毛利" align="center" prop="grossProfit" />
      <el-table-column label="权限编号" align="center" prop="prodPerm" />
      <el-table-column label="产品图片" align="center" prop="prodImg" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.prodImg" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="gmtCreate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.gmtCreate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="修改时间" align="center" prop="gmtModified" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.gmtModified, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['crm:product:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['crm:product:remove']"
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

    <!-- 添加或修改产品对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="产品名称" prop="prodName">
          <el-input v-model="form.prodName" placeholder="请输入产品名称" />
        </el-form-item>
        <el-form-item label="规格型号" prop="prodSpecs">
          <el-input v-model="form.prodSpecs" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="成本单价" prop="costPrice">
          <el-input v-model="form.costPrice" placeholder="请输入成本单价" />
        </el-form-item>
        <el-form-item label="增值税税率" prop="vatRate">
          <el-input v-model="form.vatRate" placeholder="请输入增值税税率" />
        </el-form-item>
        <el-form-item label="销售单价(含税)" prop="inclTaxPrice">
          <el-input v-model="form.inclTaxPrice" placeholder="请输入销售单价(含税)" />
        </el-form-item>
        <el-form-item label="销售单价(不含税)" prop="exclTaxPrice">
          <el-input v-model="form.exclTaxPrice" placeholder="请输入销售单价(不含税)" />
        </el-form-item>
        <el-form-item label="税额" prop="taxAmount">
          <el-input v-model="form.taxAmount" placeholder="请输入税额" />
        </el-form-item>
        <el-form-item label="售价毛利" prop="grossProfit">
          <el-input v-model="form.grossProfit" placeholder="请输入售价毛利" />
        </el-form-item>
        <el-form-item label="产品图片" prop="prodImg">
          <image-upload v-model="form.prodImg"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listProduct, getProduct, delProduct, addProduct, updateProduct } from "@/api/crm/product";

export default {
  name: "Product",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 产品表格数据
      productList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 产品图片时间范围
      daterangeGmtCreate: [],
      // 产品图片时间范围
      daterangeGmtModified: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        prodCode: null,
        prodAttr: null,
        prodType: null,
        prodName: null,
        prodBrand: null,
        prodSpecs: null,
        prodPerm: null,
        gmtCreate: null,
        gmtModified: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        prodAttr: [
          { required: true, message: "属性编号不能为空", trigger: "change" }
        ],
        prodType: [
          { required: true, message: "类型编号不能为空", trigger: "change" }
        ],
        prodName: [
          { required: true, message: "产品名称不能为空", trigger: "blur" }
        ],
        prodBrand: [
          { required: true, message: "品牌编号不能为空", trigger: "change" }
        ],
        prodSpecs: [
          { required: true, message: "规格型号不能为空", trigger: "blur" }
        ],
        prodUnit: [
          { required: true, message: "单位编号不能为空", trigger: "change" }
        ],
        costPrice: [
          { required: true, message: "成本单价不能为空", trigger: "blur" }
        ],
        vatRate: [
          { required: true, message: "增值税税率不能为空", trigger: "blur" }
        ],
        inclTaxPrice: [
          { required: true, message: "销售单价(含税)不能为空", trigger: "blur" }
        ],
        prodPerm: [
          { required: true, message: "权限编号不能为空", trigger: "change" }
        ],
        gmtCreate: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
        gmtModified: [
          { required: true, message: "修改时间不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询产品列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeGmtCreate && '' != this.daterangeGmtCreate) {
        this.queryParams.params["beginGmtCreate"] = this.daterangeGmtCreate[0];
        this.queryParams.params["endGmtCreate"] = this.daterangeGmtCreate[1];
      }
      if (null != this.daterangeGmtModified && '' != this.daterangeGmtModified) {
        this.queryParams.params["beginGmtModified"] = this.daterangeGmtModified[0];
        this.queryParams.params["endGmtModified"] = this.daterangeGmtModified[1];
      }
      listProduct(this.queryParams).then(response => {
        this.productList = response.rows;
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
        id: null,
        prodCode: null,
        prodAttr: null,
        prodType: null,
        prodName: null,
        prodBrand: null,
        prodSpecs: null,
        prodUnit: null,
        costPrice: null,
        vatRate: null,
        inclTaxPrice: null,
        exclTaxPrice: null,
        taxAmount: null,
        grossProfit: null,
        prodPerm: null,
        prodImg: null,
        gmtCreate: null,
        gmtModified: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeGmtCreate = [];
      this.daterangeGmtModified = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加产品";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getProduct(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改产品";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateProduct(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addProduct(this.form).then(response => {
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
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除产品编号为"' + ids + '"的数据项？').then(function() {
        return delProduct(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('crm/product/export', {
        ...this.queryParams
      }, `product_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
