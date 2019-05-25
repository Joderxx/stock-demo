<template>
  <div class="components-container">
    <pan-thumb :image="image" >
      <span style="font-size: 2em">
        {{$store.getters.name}}
      </span>
    </pan-thumb>

    <el-button type="primary" icon="upload" style="position: absolute;bottom: 15px;margin-left: 40px;" @click="imagecropperShow=true">
      {{$t('avatar.change')}}
    </el-button>

    <image-cropper
      v-show="imagecropperShow"
      :key="imagecropperKey"
      :width="300"
      :height="300"
      :url="url"
      lang-type="zh"
      @close="close"
      @crop-upload-success="cropSuccess"
    />
  </div>
</template>

<script>
  import ImageCropper from '@/components/ImageCropper'
  import PanThumb from '@/components/PanThumb'
  export default {
    components: { ImageCropper, PanThumb },
    data() {
      return {
        imagecropperShow: false,
        imagecropperKey: 0,
        url: process.env.BASE_API+"/avatar/update",
      }
    },
    methods: {
      cropSuccess(resData) {
        this.imagecropperShow = false
        this.imagecropperKey = this.imagecropperKey + 1
      },
      close() {
        this.imagecropperShow = false
      }
    },
    computed: {
      image(){
        return this.$store.getters.avatar
      }
    }
  }
</script>

<style scoped>
  .avatar{
    width: 200px;
    height: 200px;
    border-radius: 50%;
  }
</style>

