<template style="padding:0px">
  <div style="margin-right:1000px">
    <!-- <img class="look" src="../img/看啥，点啊.png" > -->
    <baidu-map
        class="map"
        :center="{ lng: 113.4048, lat: 23.06931 }"
        :zoom="16"
    >
      <bm-marker
          :position="{ lng: 113.4048, lat: 23.06931 }"
          :dragging="true"
          animation="BMAP_ANIMATION_BOUNCE"
      >
      </bm-marker>
    </baidu-map>
  </div>

</template>

<style scoped>
.look{
  width:275px;
  height:200px;
  margin-top: 100px;
  margin-right: 1000px;
}
.map {
  position: absolute;
  width:1238px;
  height:730px;
  margin-left: -20px;
  margin-top: -20px ;
}
</style>