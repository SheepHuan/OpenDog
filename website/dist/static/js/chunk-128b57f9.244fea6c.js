(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-128b57f9"],{"04d1":function(e,t,i){var n=i("342f"),a=n.match(/firefox\/(\d+)/i);e.exports=!!a&&+a[1]},"15fd":function(e,t,i){"use strict";i.d(t,"a",(function(){return a}));i("a4d3"),i("b64b");function n(e,t){if(null==e)return{};var i,n,a={},r=Object.keys(e);for(n=0;n<r.length;n++)i=r[n],t.indexOf(i)>=0||(a[i]=e[i]);return a}function a(e,t){if(null==e)return{};var i,a,r=n(e,t);if(Object.getOwnPropertySymbols){var o=Object.getOwnPropertySymbols(e);for(a=0;a<o.length;a++)i=o[a],t.indexOf(i)>=0||Object.prototype.propertyIsEnumerable.call(e,i)&&(r[i]=e[i])}return r}},"1c18":function(e,t,i){},"333d":function(e,t,i){"use strict";var n=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",{staticClass:"pagination-container",class:{hidden:e.hidden}},[i("el-pagination",e._b({attrs:{background:e.background,"current-page":e.currentPage,"page-size":e.pageSize,layout:e.layout,"page-sizes":e.pageSizes,total:e.total},on:{"update:currentPage":function(t){e.currentPage=t},"update:current-page":function(t){e.currentPage=t},"update:pageSize":function(t){e.pageSize=t},"update:page-size":function(t){e.pageSize=t},"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}},"el-pagination",e.$attrs,!1))],1)},a=[];i("a9e3");Math.easeInOutQuad=function(e,t,i,n){return e/=n/2,e<1?i/2*e*e+t:(e--,-i/2*(e*(e-2)-1)+t)};var r=function(){return window.requestAnimationFrame||window.webkitRequestAnimationFrame||window.mozRequestAnimationFrame||function(e){window.setTimeout(e,1e3/60)}}();function o(e){document.documentElement.scrollTop=e,document.body.parentNode.scrollTop=e,document.body.scrollTop=e}function s(){return document.documentElement.scrollTop||document.body.parentNode.scrollTop||document.body.scrollTop}function l(e,t,i){var n=s(),a=e-n,l=20,c=0;t="undefined"===typeof t?500:t;var u=function e(){c+=l;var s=Math.easeInOutQuad(c,n,a,t);o(s),c<t?r(e):i&&"function"===typeof i&&i()};u()}var c={name:"Pagination",props:{total:{required:!0,type:Number},page:{type:Number,default:1},limit:{type:Number,default:20},pageSizes:{type:Array,default:function(){return[10,20,30,50]}},layout:{type:String,default:"total, sizes, prev, pager, next, jumper"},background:{type:Boolean,default:!0},autoScroll:{type:Boolean,default:!0},hidden:{type:Boolean,default:!1}},computed:{currentPage:{get:function(){return this.page},set:function(e){this.$emit("update:page",e)}},pageSize:{get:function(){return this.limit},set:function(e){this.$emit("update:limit",e)}}},methods:{handleSizeChange:function(e){this.$emit("pagination",{page:this.currentPage,limit:e}),this.autoScroll&&l(0,800)},handleCurrentChange:function(e){this.$emit("pagination",{page:e,limit:this.pageSize}),this.autoScroll&&l(0,800)}}},u=c,d=(i("e498"),i("2877")),p=Object(d["a"])(u,n,a,!1,null,"6af373ef",null);t["a"]=p.exports},"4e82":function(e,t,i){"use strict";var n=i("23e7"),a=i("e330"),r=i("59ed"),o=i("7b0b"),s=i("07fa"),l=i("577e"),c=i("d039"),u=i("addb"),d=i("a640"),p=i("04d1"),m=i("d998"),f=i("2d00"),h=i("512ce"),g=[],v=a(g.sort),y=a(g.push),b=c((function(){g.sort(void 0)})),w=c((function(){g.sort(null)})),k=d("sort"),x=!c((function(){if(f)return f<70;if(!(p&&p>3)){if(m)return!0;if(h)return h<603;var e,t,i,n,a="";for(e=65;e<76;e++){switch(t=String.fromCharCode(e),e){case 66:case 69:case 70:case 72:i=3;break;case 68:case 71:i=4;break;default:i=2}for(n=0;n<47;n++)g.push({k:t+n,v:i})}for(g.sort((function(e,t){return t.v-e.v})),n=0;n<g.length;n++)t=g[n].k.charAt(0),a.charAt(a.length-1)!==t&&(a+=t);return"DGBEFHACIJK"!==a}})),S=b||!w||!k||!x,j=function(e){return function(t,i){return void 0===i?-1:void 0===t?1:void 0!==e?+e(t,i)||0:l(t)>l(i)?1:-1}};n({target:"Array",proto:!0,forced:S},{sort:function(e){void 0!==e&&r(e);var t=o(this);if(x)return void 0===e?v(t):v(t,e);var i,n,a=[],l=s(t);for(n=0;n<l;n++)n in t&&y(a,t[n]);u(a,j(e)),i=a.length,n=0;while(n<i)t[n]=a[n++];while(n<l)delete t[n++];return t}})},"512ce":function(e,t,i){var n=i("342f"),a=n.match(/AppleWebKit\/(\d+)\./);e.exports=!!a&&+a[1]},6724:function(e,t,i){"use strict";i("8d41");var n="@@wavesContext";function a(e,t){function i(i){var n=Object.assign({},t.value),a=Object.assign({ele:e,type:"hit",color:"rgba(0, 0, 0, 0.15)"},n),r=a.ele;if(r){r.style.position="relative",r.style.overflow="hidden";var o=r.getBoundingClientRect(),s=r.querySelector(".waves-ripple");switch(s?s.className="waves-ripple":(s=document.createElement("span"),s.className="waves-ripple",s.style.height=s.style.width=Math.max(o.width,o.height)+"px",r.appendChild(s)),a.type){case"center":s.style.top=o.height/2-s.offsetHeight/2+"px",s.style.left=o.width/2-s.offsetWidth/2+"px";break;default:s.style.top=(i.pageY-o.top-s.offsetHeight/2-document.documentElement.scrollTop||document.body.scrollTop)+"px",s.style.left=(i.pageX-o.left-s.offsetWidth/2-document.documentElement.scrollLeft||document.body.scrollLeft)+"px"}return s.style.backgroundColor=a.color,s.className="waves-ripple z-active",!1}}return e[n]?e[n].removeHandle=i:e[n]={removeHandle:i},i}var r={bind:function(e,t){e.addEventListener("click",a(e,t),!1)},update:function(e,t){e.removeEventListener("click",e[n].removeHandle,!1),e.addEventListener("click",a(e,t),!1)},unbind:function(e){e.removeEventListener("click",e[n].removeHandle,!1),e[n]=null,delete e[n]}},o=function(e){e.directive("waves",r)};window.Vue&&(window.waves=r,Vue.use(o)),r.install=o;t["a"]=r},7521:function(e,t,i){"use strict";i.r(t);var n=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",{staticClass:"app-container"},[i("div",{staticClass:"filter-container"},[i("el-input",{staticClass:"filter-item",staticStyle:{width:"200px"},attrs:{placeholder:"项目名"},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleFilter(t)}},model:{value:e.listQuery.projectName,callback:function(t){e.$set(e.listQuery,"projectName",t)},expression:"listQuery.projectName"}}),i("el-button",{directives:[{name:"waves",rawName:"v-waves"}],staticClass:"filter-item",attrs:{type:"primary",icon:"el-icon-search"},on:{click:e.handleFilter}},[e._v(" 搜索 ")]),i("el-button",{staticClass:"filter-item",staticStyle:{"margin-left":"10px"},attrs:{type:"primary",icon:"el-icon-edit"},on:{click:e.handleCreate}},[e._v(" 添加 ")])],1),i("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],key:e.tableKey,staticStyle:{width:"100%"},attrs:{data:e.list,border:"",fit:"","highlight-current-row":""},on:{"sort-change":e.sortChange}},[i("el-table-column",{attrs:{label:"序号",type:"index",align:"center",width:"80"}}),i("el-table-column",{attrs:{label:"项目编号",prop:"pid",width:"80px",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){var n=t.row;return[i("span",[e._v(e._s(n.pid))])]}}])}),i("el-table-column",{attrs:{label:"项目名",prop:"projectName",width:"150px",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){var n=t.row;return[i("span",{staticClass:"link-type",on:{click:function(t){return e.caseList(n)}}},[e._v(e._s(n.projectName))])]}}])}),i("el-table-column",{attrs:{label:"用户编号",prop:"uid",width:"80px",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){var n=t.row;return[i("span",[e._v(e._s(n.uid))])]}}])}),i("el-table-column",{attrs:{label:"项目描述",prop:"comment","min-width":"150px",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){var n=t.row;return[i("span",[e._v(e._s(n.comment))])]}}])}),i("el-table-column",{attrs:{label:"操作",align:"center",width:"200","class-name":"small-padding fixed-width"},scopedSlots:e._u([{key:"default",fn:function(t){var n=t.row,a=t.$index;return[i("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(t){return e.handleUpdate(n)}}},[e._v(" 编辑 ")]),"deleted"!=n.status?i("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(t){return e.handleDelete(n,a)}}},[e._v(" 删除 ")]):e._e()]}}])})],1),i("pagination",{directives:[{name:"show",rawName:"v-show",value:e.total>0,expression:"total>0"}],attrs:{total:e.total,page:e.listQuery.page,limit:e.listQuery.limit},on:{"update:page":function(t){return e.$set(e.listQuery,"page",t)},"update:limit":function(t){return e.$set(e.listQuery,"limit",t)},pagination:e.getList}}),i("el-dialog",{attrs:{title:e.textMap[e.dialogStatus],visible:e.dialogFormVisible},on:{"update:visible":function(t){e.dialogFormVisible=t}}},[i("el-form",{ref:"dataForm",staticStyle:{width:"400px","margin-left":"50px"},attrs:{rules:e.rules,model:e.temp,"label-position":"left","label-width":"70px"}},[i("el-form-item",{attrs:{label:"项目名"}},[i("el-input",{model:{value:e.temp.projectName,callback:function(t){e.$set(e.temp,"projectName",t)},expression:"temp.projectName"}})],1),i("el-form-item",{attrs:{label:"项目描述"}},[i("el-input",{attrs:{autosize:{minRows:2,maxRows:4},type:"textarea",placeholder:"请输入"},model:{value:e.temp.comment,callback:function(t){e.$set(e.temp,"comment",t)},expression:"temp.comment"}})],1)],1),i("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[i("el-button",{on:{click:function(t){e.dialogFormVisible=!1}}},[e._v(" 取消 ")]),i("el-button",{attrs:{type:"primary"},on:{click:function(t){"create"===e.dialogStatus?e.createData():e.updateData()}}},[e._v(" 确认 ")])],1)],1)],1)},a=[],r=i("5530"),o=i("15fd"),s=(i("d81d"),i("4e82"),i("c740"),i("a434"),i("d3b7"),i("159b"),i("6724")),l=i("333d"),c=["createdTime","updatedTime","tid"],u={name:"projectView",components:{Pagination:l["a"]},directives:{waves:s["a"]},data:function(){return{tableKey:0,list:null,total:0,listLoading:!0,listQuery:{page:1,limit:20,projectName:"",sort:"+id"},sortOptions:[{label:"ID Ascending",key:"+id"},{label:"ID Descending",key:"-id"}],temp:{pid:-1,projectName:"",uid:this.$store.getters.uid,comment:""},dialogFormVisible:!1,dialogStatus:"",textMap:{update:"Edit",create:"Create"},rules:{type:[{required:!0,message:"type is required",trigger:"change"}],timestamp:[{type:"date",required:!0,message:"timestamp is required",trigger:"change"}],title:[{required:!0,message:"title is required",trigger:"blur"}]},downloadLoading:!1}},created:function(){this.getList()},methods:{getList:function(){var e=this;this.listLoading=!0,this.$store.dispatch("case/select_user_projects").then((function(t){var i=t.stateCode,n=t.data;n=n.map((function(e){var t=Object(r["a"])({},e),i=(t.createdTime,t.updatedTime,t.tid,Object(o["a"])(t,c));return i})),1e3==i&&(e.list=n,e.total=n.length),setTimeout((function(){e.listLoading=!1}),1500)}))},handleFilter:function(){""!=this.listQuery.projectName?(this.querySearch(),this.listQuery.page=1,this.total=this.list.length):this.getList()},sortChange:function(e){var t=e.prop,i=e.order;"id"===t&&this.sortByID(i)},sortByID:function(e){this.listQuery.sort="ascending"===e?"+id":"-id",this.handleFilter()},setTemp:function(e,t,i){this.temp={pid:e,projectName:t,comment:i}},resetTemp:function(){this.temp={pid:-1,projectName:"",comment:""}},handleCreate:function(){var e=this;this.resetTemp(),this.dialogStatus="create",this.dialogFormVisible=!0,this.$nextTick((function(){e.$refs["dataForm"].clearValidate()}))},createData:function(){var e=this;this.$refs["dataForm"].validate((function(t){t&&e.$store.dispatch("case/create_project",{projectName:e.temp.projectName,comment:e.temp.comment}).then((function(t){if(1e3!=t.state)return!1;var i=t.data.project,n=i.pid,a=i.projectName,r=i.comment;e.setTemp(n,a,r),e.list.unshift(e.temp),e.dialogFormVisible=!1,e.$notify({title:"Success",message:"Created Successfully",type:"success",duration:2e3})}))}))},handleUpdate:function(e){var t=this;this.setTemp(e.pid,e.projectName,e.comment),this.dialogStatus="update",this.dialogFormVisible=!0,this.$nextTick((function(){t.$refs["dataForm"].clearValidate()}))},updateData:function(){var e=this;this.$refs["dataForm"].validate((function(t){if(t){var i={pid:e.temp.pid,tid:0,projectName:e.temp.projectName,comment:e.temp.comment};console.log(i),e.$store.dispatch("case/update_project",i).then((function(t){if(console.log(t),1e3!=t)return!1;var i=e.list.findIndex((function(t){return t.pid===e.temp.pid}));console.log(i),e.list.splice(i,1,e.temp),e.dialogFormVisible=!1,e.$notify({title:"Success",message:"Update Successfully",type:"success",duration:2e3})}))}}))},handleDelete:function(e,t){var i=this;this.$store.dispatch("case/delete_project",[e.pid]).then((function(e){1e3==e&&(i.$notify({title:"Success",message:"Delete Successfully",type:"success",duration:2e3}),i.list.splice(t,1))}))},querySearch:function(){var e=[],t=this;this.list.forEach((function(i){i.projectName.toLowerCase().indexOf(t.listQuery.projectName.toLowerCase())>-1&&e.push(i)})),this.list=e},caseList:function(e){this.$router.push({name:"caseView",params:{pid:e.pid}})}}},d=u,p=i("2877"),m=Object(p["a"])(d,n,a,!1,null,null,null);t["default"]=m.exports},"8d41":function(e,t,i){},addb:function(e,t,i){var n=i("4dae"),a=Math.floor,r=function(e,t){var i=e.length,l=a(i/2);return i<8?o(e,t):s(e,r(n(e,0,l),t),r(n(e,l),t),t)},o=function(e,t){var i,n,a=e.length,r=1;while(r<a){n=r,i=e[r];while(n&&t(e[n-1],i)>0)e[n]=e[--n];n!==r++&&(e[n]=i)}return e},s=function(e,t,i,n){var a=t.length,r=i.length,o=0,s=0;while(o<a||s<r)e[o+s]=o<a&&s<r?n(t[o],i[s])<=0?t[o++]:i[s++]:o<a?t[o++]:i[s++];return e};e.exports=r},d998:function(e,t,i){var n=i("342f");e.exports=/MSIE|Trident/.test(n)},e498:function(e,t,i){"use strict";i("1c18")}}]);