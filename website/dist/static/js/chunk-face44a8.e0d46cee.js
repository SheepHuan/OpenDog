(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-face44a8"],{"2ac0":function(e,t,a){"use strict";a.r(t);var s=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"textbox"},[a("el-container",{staticClass:"login"},[a("el-header",[a("div",{staticStyle:{width:"100%",height:"60px","background-color":"#303133",position:"fixed !important",top:"0px",left:"0px"}},[a("el-page-header",{attrs:{content:e.caseInfo.caseName},on:{back:e.goBack}})],1)])],1),a("div",{staticClass:"infobox",staticStyle:{"margin-bottom":"20px"}},[a("div",{staticClass:"infobox",staticStyle:{"margin-top":"20px"}},[a("svg-icon",{attrs:{"icon-class":"case","class-name":"icon-format"}}),a("div",{staticClass:"textbox",staticStyle:{"margin-left":"20px"}},[a("div",[e._v("Android版本号:"+e._s(e.DeviceInfo.OSName))]),a("div",{staticStyle:{"margin-top":"10px"}},[e._v(e._s(e.AppInfo.package))])])],1),a("div",{staticClass:"infobox"},[a("svg-icon",{attrs:{"icon-class":"phone","class-name":"icon-format"}}),a("div",{staticClass:"textbox",staticStyle:{"margin-left":"20px"}},[a("span",[a("strong",{staticStyle:{"font-size":"25px"}},[e._v(e._s(e.DeviceInfo.deviceName))])]),a("span",[e._v("SerialNum:"+e._s(e.DeviceInfo.SerialNum))]),a("span",[e._v("OpenGL:"+e._s(e.DeviceInfo.OpenGL))]),a("span",[e._v("CPU:"+e._s(e.DeviceInfo.CPUInfo))])])],1),e._l(e.infoDisplay,(function(t,s){return a("div",{key:t,staticClass:"infobox"},[a("svg-icon",{attrs:{"icon-class":e.iconList[s],"class-name":"icon-format"}}),a("div",{staticClass:"textbox",staticStyle:{"margin-left":"20px"}},[a("span",[a("strong",{staticStyle:{"font-size":"25px"}},[e._v(e._s("userName"===s?"创建者":"上传时间"))])]),a("span",[e._v(e._s(t))])])],1)}))],2),a("div",{staticClass:"collapse-box"},[a("el-collapse",{on:{change:e.initCharts},model:{value:e.activeNames,callback:function(t){e.activeNames=t},expression:"activeNames"}},[a("el-collapse-item",{attrs:{title:"概览",name:"general"}},[a("div",{staticClass:"infobox"},e._l(e.Avg,(function(t,s){return a("div",{key:s,staticClass:"textbox"},[a("span",[e._v(e._s(s))]),a("span",{staticStyle:{"font-size":"25px"}},[a("strong",[e._v(e._s(t))])])])})),0)]),a("el-collapse-item",{staticClass:"chart-box",attrs:{title:"FPS",name:"FPSList"}},[a("div",{ref:"FPSList",staticClass:"chartBox"})]),a("el-collapse-item",{staticClass:"chart-box2",attrs:{title:"CPU",name:"CPU"}},[a("div",{ref:"CPUUsage",staticClass:"chartBox"}),a("div",{ref:"CPUClock",staticClass:"chartBox",staticStyle:{"margin-top":"15px"}})]),a("el-collapse-item",{staticClass:"chart-box",attrs:{title:"Memory",name:"memoryData"}},[a("div",{ref:"memoryData",staticClass:"chartBox"})]),a("el-collapse-item",{staticClass:"chart-box",attrs:{title:"Temperature",name:"temperature"}},[a("div",{ref:"temperature",staticClass:"chartBox"})])],1)],1)],1)},i=[],o=(a("b0c0"),a("5ded"),a("6724")),n=a("313e"),c={name:"caseDetail",directives:{waves:o["a"]},data:function(){return{activeNames:["general"],Num:[],AppInfo:{dateTime:"",package:""},DeviceInfo:{SerialNum:"",OSName:"",OpenGL:"",deviceName:"",CPUInfo:""},Avg:{"Avg[FPS]":0,"Peak(Memory)[MB]":0,"Avg(Memory)[MB]":0},FPSList:[],memoryData:{Memory:[],NativePss:[],Gfx:[],EGL:[],GL:[],Unknown:[],PrivateClean:[],PrivateDirty:[]},temperature:{CPUTemp:[],GpuTemp:[],BatteryTemp:[]},CPUClock:{CPUClock0:[],CPUClock1:[],CPUClock2:[],CPUClock3:[],CPUClock4:[],CPUClock5:[],CPUClock6:[],CPUClock7:[]},CPUUsage:{CPUUsage0:[],CPUUsage1:[],CPUUsage2:[],CPUUsage3:[],CPUUsage4:[],CPUUsage5:[],CPUUsage6:[],CPUUsage7:[]},caseInfo:{caseId:0,caseName:"",comment:""},iconList:{userName:"role",uploadTime:"upload"},infoDisplay:{userName:this.$store.getters.name,uploadTime:""},echartState:{general:!0,FPSList:!1,CPU:!1,memoryData:!1,temperature:!1}}},created:function(){var e=this;this.openFullScreen(),this.caseInfo.caseId=this.$route.params.caseId,this.$store.dispatch("case/get_case_detail",{caseId:Array.of(this.caseInfo.caseId)}).then((function(t){var a=t.stateCode,s=t.data;1e3===a&&(e.setCaseInfo(s.caseId,s.caseName,s.comment),e.infoDisplay.uploadTime=s.createdTime)})),this.$store.dispatch("case/get_case_data",this.caseInfo.caseId).then((function(t){var a=t.stateCode,s=t.data;1e3===a&&s&&e.getData(s)})),this.closeFullScreen(this.openFullScreen())},methods:{getData:function(e){this.setNum(e),this.setAppInfo(e),this.setDeviceInfo(e),this.setAvg(e),this.setFPSList(e),this.setMemoryData(e),this.setTemperature(e),this.setCPUClock(e),this.setCPUUsage(e)},setCaseInfo:function(e,t,a){this.caseInfo.caseId=e,this.caseInfo.caseName=t,this.caseInfo.comment=a},setNum:function(e){this.Num=e.Data.Table.Num},setAppInfo:function(e){this.AppInfo.dateTime=e.AppInfo.Date,this.AppInfo.package=e.AppInfo.Package},setDeviceInfo:function(e){this.DeviceInfo.SerialNum=e.DeviceInfo.SerialNum,this.DeviceInfo.OSName=e.DeviceInfo["OS"],this.DeviceInfo.OpenGL=e.DeviceInfo.OpenGL,this.DeviceInfo.CPUInfo=e.DeviceInfo["CPU Info"],this.DeviceInfo.deviceName=e.DeviceInfo["Device Name"]},setAvg:function(e){this.Avg["Avg[FPS]"]=e.Data.Avg["Avg(FPS)"],this.Avg["Peak(Memory)[MB]"]=e.Data.Avg["Peak(Memory)[MB]"],this.Avg["Avg(Memory)[MB]"]=e.Data.Avg["Avg(Memory)[MB]"]},setFPSList:function(e){this.FPSList=e.Data.Table["FPS"]},setMemoryData:function(e){this.memoryData.Memory=e.Data.Table["Memory[MB]"],this.memoryData.NativePss=e.Data.Table["NativePss[MB]"],this.memoryData.Gfx=e.Data.Table["Gfx[MB]"],this.memoryData.EGL=e.Data.Table["EGL[MB]"],this.memoryData.GL=e.Data.Table["GL[MB]"],this.memoryData.Unknown=e.Data.Table["Unknown[MB]"],this.memoryData.PrivateClean=e.Data.Table["PrivateClean[MB]"],this.memoryData.PrivateDirty=e.Data.Table["PrivateDirty[MB]"]},setTemperature:function(e){this.temperature.CPUTemp=e.Data.Table.CpuTemp,this.temperature.GpuTemp=e.Data.Table.GpuTemp,this.temperature.BatteryTemp=e.Data.Table.BatteryTemp},setCPUClock:function(e){this.CPUClock.CPUClock0=e.Data.Table["CPUClock0[MHz]"],this.CPUClock.CPUClock1=e.Data.Table["CPUClock1[MHz]"],this.CPUClock.CPUClock2=e.Data.Table["CPUClock2[MHz]"],this.CPUClock.CPUClock3=e.Data.Table["CPUClock3[MHz]"],this.CPUClock.CPUClock4=e.Data.Table["CPUClock4[MHz]"],this.CPUClock.CPUClock5=e.Data.Table["CPUClock5[MHz]"],this.CPUClock.CPUClock6=e.Data.Table["CPUClock6[MHz]"],this.CPUClock.CPUClock7=e.Data.Table["CPUClock7[MHz]"]},setCPUUsage:function(e){this.CPUUsage.CPUUsage0=e.Data.Table["CPUUsage0[%]"],this.CPUUsage.CPUUsage1=e.Data.Table["CPUUsage1[%]"],this.CPUUsage.CPUUsage2=e.Data.Table["CPUUsage2[%]"],this.CPUUsage.CPUUsage3=e.Data.Table["CPUUsage3[%]"],this.CPUUsage.CPUUsage4=e.Data.Table["CPUUsage4[%]"],this.CPUUsage.CPUUsage5=e.Data.Table["CPUUsage5[%]"],this.CPUUsage.CPUUsage6=e.Data.Table["CPUUsage6[%]"],this.CPUUsage.CPUUsage7=e.Data.Table["CPUUsage7[%]"]},goBack:function(){this.$router.push({name:"caseView",params:{pid:this.$store.getters.pid}})},openFullScreen:function(){var e=this.$loading({lock:!0,text:"Loading",spinner:"el-icon-loading",background:"rgba(0, 0, 0, 0.7)"});return e},closeFullScreen:function(e){e.close()},dataToSeries:function(e){var t=[];for(var a in e)t.push({name:a,type:"line",smooth:!0,data:e[a]});return t},listToSeries:function(e,t){return{name:e,type:"line",smooth:!0,data:t}},initCharts:function(e){this.openFullScreen();var t=e[e.length-1];console.log("cn",t),console.log("val",e),t&&!this.echartState[t]&&("CPU"==t?(this.initSingleCharts("CPUUsage"),this.initSingleCharts("CPUClock")):"general"!=t&&this.initSingleCharts(t),this.echartState[t]=!0),this.closeFullScreen(this.openFullScreen())},initSingleCharts:function(e){var t=n["b"](this.$refs[e]),a=this[e];a="FPSList"!=e?this.dataToSeries(a):this.listToSeries(e,a),console.log("data",a),t.setOption({title:{text:e},xAxis:{data:this.Num},yAxis:{type:"value",scale:!0},grid:[{left:"5%",right:"5%",bottom:"20%"}],series:a,dataZoom:[{type:"slider",top:"93%",start:0,end:100,textStyle:{color:"#8392A5"},handleIcon:"M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z",handleSize:"80%",dataBackground:{areaStyle:{color:"#8392A5"},lineStyle:{opacity:.8,color:"#8392A5"}},handleStyle:{color:"#fff",shadowBlur:3,shadowColor:"rgba(0, 0, 0, 0.6)",shadowOffsetX:2,shadowOffsetY:2}},{show:!0,xAxisIndex:0,type:"inside",top:"90%",start:0,end:100}],legend:{},tooltip:{trigger:"axis",axisPointer:{type:"cross"}}})}}},l=c,r=(a("df7ca"),a("2877")),C=Object(r["a"])(l,s,i,!1,null,"26089662",null);t["default"]=C.exports},"5ded":function(e,t,a){"use strict";var s=a("23e7"),i=a("da84"),o=a("d039"),n=a("68ee"),c=a("8418"),l=i.Array,r=o((function(){function e(){}return!(l.of.call(e)instanceof e)}));s({target:"Array",stat:!0,forced:r},{of:function(){var e=0,t=arguments.length,a=new(n(this)?this:l)(t);while(t>e)c(a,e,arguments[e++]);return a.length=t,a}})},6724:function(e,t,a){"use strict";a("8d41");var s="@@wavesContext";function i(e,t){function a(a){var s=Object.assign({},t.value),i=Object.assign({ele:e,type:"hit",color:"rgba(0, 0, 0, 0.15)"},s),o=i.ele;if(o){o.style.position="relative",o.style.overflow="hidden";var n=o.getBoundingClientRect(),c=o.querySelector(".waves-ripple");switch(c?c.className="waves-ripple":(c=document.createElement("span"),c.className="waves-ripple",c.style.height=c.style.width=Math.max(n.width,n.height)+"px",o.appendChild(c)),i.type){case"center":c.style.top=n.height/2-c.offsetHeight/2+"px",c.style.left=n.width/2-c.offsetWidth/2+"px";break;default:c.style.top=(a.pageY-n.top-c.offsetHeight/2-document.documentElement.scrollTop||document.body.scrollTop)+"px",c.style.left=(a.pageX-n.left-c.offsetWidth/2-document.documentElement.scrollLeft||document.body.scrollLeft)+"px"}return c.style.backgroundColor=i.color,c.className="waves-ripple z-active",!1}}return e[s]?e[s].removeHandle=a:e[s]={removeHandle:a},a}var o={bind:function(e,t){e.addEventListener("click",i(e,t),!1)},update:function(e,t){e.removeEventListener("click",e[s].removeHandle,!1),e.addEventListener("click",i(e,t),!1)},unbind:function(e){e.removeEventListener("click",e[s].removeHandle,!1),e[s]=null,delete e[s]}},n=function(e){e.directive("waves",o)};window.Vue&&(window.waves=o,Vue.use(n)),o.install=n;t["a"]=o},"8d41":function(e,t,a){},9904:function(e,t,a){},df7ca:function(e,t,a){"use strict";a("9904")}}]);