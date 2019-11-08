<template>
  <div class="card">
    <div class="title">个人画像</div>
    <a-row :gutter="20">
      <a-col :span="8">
        <div class="background graphics-3in1"></div>
      </a-col>
      <a-col :span="8">
        <div class="background graphics-3in1"></div>
      </a-col>
      <a-col :span="8">
        <div class="background graphics-3in1"></div>
      </a-col>
    </a-row>
    <a-row :gutter="20">
      <a-col :span="12">
        <div class="background graphics-2in1" id="commentCount"></div>
      </a-col>
      <a-col :span="12">
        <div class="background graphics-2in1"></div>
      </a-col>
    </a-row>
    <a-row :gutter="20">
      <a-col :span="12">
        <div class="background graphics-2in1"></div>
      </a-col>
      <a-col :span="12">
        <div class="background graphics-2in1"></div>
      </a-col>
    </a-row>
    <a-row :gutter="20">
      <a-col :span="24">
        <div class="background graphics-1 bottom"></div>
      </a-col>
    </a-row>
  </div>
</template>

<script src="./Persona.js">
</script>

<style lang="less" scoped>
@import "./Persona.less";
</style>