/**
 * 负责创建option
 *data:要初始化的数据
 *ele:大类id
 */
function createOption(data,ele,val,fidEle,val2){

    ele.empty(); // 清空子节点

    // 添加默认的
    ele.append("<option>==请选择==</option>");

    // 2.循环创建option
    for(var i =0;i<data.length;i++){

        var gtype = data[i];

        // 3.创建一个option
        var option = $(document.createElement("option"));

        // 4.给option属性赋值
        option.val(gtype.id);
        option.text(gtype.gname);

        // 5.把option元素添加select里面
        ele.append(option);
    }

    // 判断是否选中大类
    if(val){

        // 选中大类
        ele.val(val);

        // 初始化小类
        changeFid(val,fidEle,val2);
    }

}

/**
 * 初始化大类
 * gpIdEle:大类的元素id
 * val:被选中的大类
 */
function initGoodsType(gpIdEle,val,fidEle,val2){

    // 1.获取所有商品类别
    $.getJSON("GoodsTypeServlet/getAllGoodsType",function(data){

        // 1.获取大类的元素对象
        var gpidEle = $(gpIdEle);

        var array = new Array();

        for(var i =0;i<data.length;i++){
            if(data[i].gpid == 0){
                array.push(data[i]);
            }
        }

        createOption(array,gpidEle,val,fidEle,val2);
    })
}

// 改变小类
function changeFid(pid,gfid,val2){

    // 根据大类id查询小类
    $.getJSON("GoodsTypeServlet/getGoodsListByPid?id="+pid,function(data){
        createOption(data,gfid,val2);
    })
}