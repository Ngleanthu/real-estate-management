<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="buildingEditURL" value="/admin/building-edit"/>
<c:url var="buildingAPI" value = "/api/building"/>
<html>
<head>
    <title>sửa toà nhà</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try { ace.settings.check('breadcrumbs', 'fixed') } catch (e) { }
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Home</a>
                </li>
                <li class="active">Dashboard</li>
            </ul><!-- /.breadcrumb -->

            <div class="nav-search" id="nav-search">
                <form class="form-search">
							<span class="input-icon">
								<input type="text" placeholder="Search ..." class="nav-search-input"
                                       id="nav-search-input" autocomplete="off" />
								<i class="ace-icon fa fa-search nav-search-icon"></i>
							</span>
                </form>
            </div><!-- /.nav-search -->
        </div>

        <div class="page-content">
            <div class="ace-settings-container" id="ace-settings-container">
                <div class="ace-settings-box clearfix" id="ace-settings-box">
                    <div class="pull-left width-50">
                        <div class="ace-settings-item">
                            <div class="pull-left">
                                <select id="skin-colorpicker" class="hide">
                                    <option data-skin="no-skin" value="#438EB9">#438EB9</option>
                                    <option data-skin="skin-1" value="#222A2D">#222A2D</option>
                                    <option data-skin="skin-2" value="#C6487E">#C6487E</option>
                                    <option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
                                </select>
                            </div>
                            <span>&nbsp; Choose Skin</span>
                        </div>

                        <div class="ace-settings-item">
                            <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-navbar" />
                            <labABCel class="lbl" for="ace-settings-navbar"> Fixed Navbar</labABCel>
                        </div>

                        <div class="ace-settings-item">
                            <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-sidebar" />
                            <label class="lbl" for="ace-settings-sidebar"> Fixed Sidebar</label>
                        </div>

                        <div class="ace-settings-item">
                            <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-breadcrumbs" />
                            <label class="lbl" for="ace-settings-breadcrumbs"> Fixed Breadcrumbs</label>
                        </div>

                        <div class="ace-settings-item">
                            <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl" />
                            <label class="lbl" for="ace-settings-rtl"> Right To Left (rtl)</label>
                        </div>

                        <div class="ace-settings-item">
                            <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-add-container" />
                            <label class="lbl" for="ace-settings-add-container">
                                Inside
                                <b>.container</b>
                            </label>
                        </div>
                    </div><!-- /.pull-left -->

                    <div class="pull-left width-50">
                        <div class="ace-settings-item">
                            <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-hover" />
                            <label class="lbl" for="ace-settings-hover"> Submenu on Hover</label>
                        </div>

                        <div class="ace-settings-item">
                            <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-compact" />
                            <label class="lbl" for="ace-settings-compact"> Compact Sidebar</label>
                        </div>

                        <div class="ace-settings-item">
                            <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-highlight" />
                            <label class="lbl" for="ace-settings-highlight"> Alt. Active Item</label>
                        </div>
                    </div><!-- /.pull-left -->
                </div><!-- /.ace-settings-box -->
            </div><!-- /.ace-settings-container -->

            <div class="page-header">
                <h1>
                    Dashboard
                    <small>
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        overview &amp; stats
                    </small>
                </h1>
            </div><!-- /.page-header -->
            <div class="row" style="font-family: 'Times New Roman', Times, serif;">
                <form:form modelAttribute="buildingEdit" action="${buildingEditURL}" id="listForm" method="GET" class="form-horizontal">
                    <div class="col-xs-12">
                            <div class="form-group">
                                <label class="col-xs-3">Tên toà nhà</label>
                                <div class="col-xs-9">
                                    <form:input path="name" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Quận hiện có</label>
                                <div class="col-xs-9">
                                    <form:select path="district" class="form-control">
                                        <form:option value="" label="--Chọn Quận--"></form:option>
                                        <form:options items="${districts}"></form:options>

                                    </form:select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Phường</label>
                                <div class=" col-xs-9">
                                    <form:input path="ward" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Đường</label>
                                <div class=" col-xs-9">
                                    <form:input path="street" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Kết cấu</label>
                                <div class=" col-xs-9">
                                    <form:input path="structure" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Số tầng hầm</label>
                                <div class=" col-xs-9">
                                    <form:input path="numberOfBasement" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Diện tích sàn</label>
                                <div class=" col-xs-9">
                                    <form:input path="floorArea" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Hướng</label>
                                <div class=" col-xs-9">
                                    <form:input path="direction" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Hạng</label>
                                <div class=" col-xs-9">
                                    <form:input path="level" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Diện tích thuê</label>
                                <div class=" col-xs-9">
                                    <form:input path="rentAreas" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Giá thuê</label>
                                <div class=" col-xs-9">
                                    <form:input path="rentPrice" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Mô tả giá</label>
                                <div class=" col-xs-9">
                                    <form:input path="rentPriceDescription" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Phí dịch vụ</label>
                                <div class=" col-xs-9">
                                    <form:input path="serviceFee" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Phí ô tô</label>
                                <div class=" col-xs-9">
                                    <form:input path="carFee" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Phí mô tô</label>
                                <div class=" col-xs-9">
                                    <form:input path="motoFee" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Phí ngoài giờ</label>
                                <div class=" col-xs-9">
                                    <form:input path="overtimeFee" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Tiền điện</label>
                                <div class=" col-xs-9">
                                    <form:input path="electricityFee" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Thanh toán</label>
                                <div class=" col-xs-9">
                                    <form:input path="payment" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Thời hạn thuê</label>
                                <div class=" col-xs-9">
                                    <form:input path="rentTime" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Thời gian trang trí</label>
                                <div class=" col-xs-9">
                                    <form:input path="decorationTime" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Tên quản lí</label>
                                <div class=" col-xs-9">
                                    <form:input path="managerName" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Số điện thoại quản lí</label>
                                <div class=" col-xs-9">
                                    <form:input path="managerPhone" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Phí môi giới</label>
                                <div class=" col-xs-9">
                                    <form:input path = "brokerageFee" cssClass="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Loại toà nhà</label>
                                <div class="col-xs-9" >
                                    <c:if test="${empty buildingEdit.id}">
                                        <form:checkboxes  path="typeCode" items="${typeCodes}"></form:checkboxes>
                                    </c:if>
                                    <c:if test="${not empty buildingEdit.id}">
                                        <span id="typeCode">

                                        </span>
                                    </c:if>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Ghi chú</label>
                                <div class=" col-xs-9">
                                    <form:input path="note" cssClass="form-control"/>
                                </div>
                            </div>
                        <div class="form-group">
                            <label class="col-sm-3 no-padding-right">Hình đại diện</label>
                            <input class="col-sm-3 no-padding-right" type="file" id="uploadImage"/>
                            <div class="col-sm-9">
                                <c:if test="${not empty buildingEdit.image}">
                                    <c:set var="imagePath" value="/repository${buildingEdit.image}"/>
                                    <img src="${imagePath}" id="viewImage" width="300px" height="300px" style="margin-top: 50px">
                                </c:if>
                                <c:if test="${empty buildingEdit.image}">
                                    <img src="/admin/img/img1.jpeg" id="viewImage" width="300px" height="300px">
                                </c:if>
                            </div>
                        </div>

                        <div class="form-group">
                                <label class="col-xs-3"></label>
                                <div class=" col-xs-9">
                                    <c:if test="${empty buildingEdit.id}">
                                        <button class="btn btn-primary" id="btnAddOrUpdateBuilding">
                                            Thêm toà nhà
                                        </button>
                                        <a href='<c:url value='/admin/building-list'/>' class="btn btn-primary">
                                            Huỷ thao tác
                                        </a>
                                    </c:if>
                                    <c:if test="${not empty buildingEdit.id}">
                                        <button class="btn btn-primary" id="btnAddOrUpdateBuilding">
                                            Cập nhật toà nhà
                                        </button>
                                        <a href='<c:url value='/admin/building-list'/>' class="btn btn-primary">
                                            Huỷ thao tác
                                        </a>
                                    </c:if>
                                    <img src="/img/loading.gif" style="display: none; height: 100px" id="loading_image">
                                </div>

                            </div>
                        <form:hidden path="id" id="buildingId"></form:hidden>
                    </div>


                </form:form>

            </div>
        </div>
    </div><!-- /.span -->

</div>

<script>
    var imageBase64 = '';
    var imageName = '';
    $(document).ready(function() {

        var buildingId = $('#buildingId').val();
        if (buildingId != "") {
            loadTypeCode(buildingId);
        }

        $('#btnAddOrUpdateBuilding').click(function(e){
            e.preventDefault();
            var formData = $('#listForm').serializeArray();
            var data = {};
            var typeCode = [];
            if($('#buildingId').val() != ""){
                $.each(formData, function(i, it){
                    data["" + it.name + ""] = it.value;
                    if ('' !== imageBase64) {
                        data['imageBase64'] = imageBase64;
                        data['imageName'] = imageName;
                    }
                });
                typeCode = $('#typeCode').find('input[type = checkbox]:checked').map(function(){
                    return $(this).val();
                }).get();
            }
            else{
                $.each(formData, function(i, it){
                    if(it.name != 'typeCode') {
                        data["" + it.name + ""] = it.value;
                    }
                    else {
                        typeCode.push(it.value);
                    }
                    if ('' !== imageBase64) {
                        data['imageBase64'] = imageBase64;
                        data['imageName'] = imageName;
                    }
                });
            }
            var type = "";
            for(var i=0; i<typeCode.length; i++){
                type += typeCode[i];
                if(i < typeCode.length-1){
                    type += ",";
                }
            }
            data['typeCode'] = type;
            if($('#buildingId').val() != ""){
                if(type.length != 0){
                    updateAndCreateBuilding(data);
                    window.location.href = "<c:url value='/admin/building-list?message=success'/>";
                }
                else {
                    window.location.href = "<c:url value='/admin/building-edit-"+buildingId+"?typeCode=typeCode_require'/>";
                }
            }
            else {
                updateAndCreateBuilding(data);
                window.location.href = "<c:url value='/admin/building-list?message=success'/>";
            }
        });
    });

    function updateAndCreateBuilding(data){
        $('#loading_image').show();
        $.ajax({
            url: "${buildingAPI}",
            type: "POST",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                $('#loading_image').hide();
                console.log("success");
                window.location.href = "<c:url value='/admin/building-list?message=success'/>";
            },
            error: function (response) {
                $('#loading_image').hide();
                console.log("fail");
                window.location.href = "<c:url value='/admin/building-list?message=error'/>";
            }
        });
    }

    function loadTypeCode(buildingId){
        $.ajax({
            type: "GET",
            url: "${buildingAPI}/" + buildingId + "/typeCodes",
            dataType: "json",
            success : function(response){
                var row = '';
                $.each(response.data, function (index,item) {
                    // row += '<input type="checkbox" name="checktype" value="'+ item.typeCode+'" '+ item.checked + '/>';
                    // row += '<label>' + item.fullName +'</label>';
                    row += '<input type="checkbox" name="checktype" value="' + item.typeCode + '" ' + (item.checked ? 'checked' : '') + '/>';
                    row += '<label>' + item.fullName + '</label>';
                });
                $('#typeCode').html(row);
                console.log("success");
            },
            error: function(response){
                console.log("failed");
                console.log(response);
            }
        });
    }

    $('#uploadImage').change(function (event) {
        var reader = new FileReader();
        var file = $(this)[0].files[0];
        reader.onload = function(e){
            imageBase64 = e.target.result;
            imageName = file.name; // ten hinh khong dau, khoang cach. vd: a-b-c
        };
        reader.readAsDataURL(file);
        openImage(this, "viewImage");
    });

    function openImage(input, imageView) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#' +imageView).attr('src', reader.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }

</script>
</body>
</html>

