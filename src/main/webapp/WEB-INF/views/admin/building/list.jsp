
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="buildingListURL" value="/admin/building-list"/>;
<c:url var="buildingAPI" value="/api/building"/>;
<c:url var="buildingAPIUser" value="/api/user"/>;

<html>
<head>
  <title>Danh sách toà nhà</title>
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

      <div class="page-header">
        <h1>
          Dashboard
          <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            Quản lí toà nhà
          </small>
        </h1>
      </div><!-- /.page-header -->

      <div class="col-xs-12">
        <div class="widget-box">
          <div class="widget-header">
            <h4 class="widget-title">Tìm kiếm</h4>
            <div class="widget-toolbar">
              <a href="#" data-action="collapse">
                <i class="ace-icon fa fa-chevron-up"></i>
              </a>
            </div>
          </div>
          <div class="widget-body" style="font-family:'Times New Roman', Times, serif">
            <div class="widget-main">
              <form:form modelAttribute="modelSearch" action="${buildingListURL}" id="listForm" method="GET">
                <div class="row">
                  <div class="form-group">
                    <div class="col-xs-12">
                      <div class="col-xs-6">
                        <label class="name">Tên toà nhà</label>
                          <%--                        <input type="text" class="form-control" name="name" value="${modelSearch.name}">--%>
                        <form:input path="name" cssClass="form-control"/>
                      </div>
                      <div class="col-xs-6">
                        <label class="name">Diện tích sàn</label>
                          <%--                        <input type="number" class="form-control" name="floorArea" value="">--%>
                        <form:input path="floorArea" cssClass="form-control"/>
                      </div>
                    </div>
                    <div class="col-xs-12">
                      <div class="col-xs-2">
                        <label class="name">Quận hiện có</label>
                        <form:select path="district" class="form-control">
                          <form:option value="" label="--Chọn Quận--"></form:option>
                          <form:options items="${districts}"></form:options>
                        </form:select>

                      </div>
                      <div class="col-xs-5">
                        <label class="name">Phường</label>
                          <%--                        <input type="text" class="form-control" name="ward" value="${modelSearch.ward}">--%>
                        <form:input path="ward" cssClass="form-control"/>
                      </div>
                      <div class="col-xs-5">
                        <label class="name">Đường</label>
                        <form:input path="street" cssClass="form-control"/>
                      </div>
                    </div>
                    <div class="col-xs-12">
                      <div class="col-xs-4">
                        <label class="name">Số tầng hầm</label>
                        <form:input path="numberOfBasement" cssClass="form-control"/>
                      </div>
                      <div class="col-xs-4">
                        <label class="name">Hướng</label>

                        <form:input path="direction" cssClass="form-control"/>
                      </div>
                      <div class="col-xs-4">
                        <label class="name">Hạng</label>
                        <form:input path="level" cssClass="form-control"/>
                      </div>
                    </div>
                    <div class="col-xs-12">
                      <div class="col-xs-3">
                        <label class="name">Diện tích từ</label>
                        <form:input path="rentAreaFrom" cssClass="form-control"/>
                      </div>
                      <div class="col-xs-3">
                        <label class="name">Diện tích đến</label>
                        <form:input path="rentAreaTo" cssClass="form-control"/>
                      </div>
                      <div class="col-xs-3">
                        <label class="name">Giá thuê từ</label>
                        <form:input path="rentPriceFrom" cssClass="form-control"/>
                      </div>
                      <div class="col-xs-3">
                        <label class="name">Giá thuê đến</label>
                        <form:input path="rentPriceTo" cssClass="form-control"/>
                      </div>
                    </div>
                    <div class="col-xs-12">
                      <div class="col-xs-5">
                        <label class="name">Tên quản lí</label>
                        <form:input path="managerName" cssClass="form-control"/>
                      </div>
                      <div class="col-xs-4">
                        <label class="name">Số điện thoại quản lí</label>
                        <form:input path="managerPhone" cssClass="form-control"/>
                      </div>
                      <div class="col-xs-3">
                        <label class="name">Chọn nhân viên phụ trách</label>
                        <form:select path="staffId" cssClass="form-control">
                          <form:option value="" label="--Chọn nhân viên phụ trách--"></form:option>
                          <form:options items="${staffs}"></form:options>
                        </form:select>
                      </div>
                      <div class="form-group">
                        <div class="col-xs-12">
                          <div class="col-xs-10">
                            <form:checkboxes path="type" items="${typeCodes}"></form:checkboxes>
                          </div>

                        </div>
                      </div>
                      <div class="form-group ">
                        <div class="col-xs-12">
                          <button class="btn btn-xs btn-danger" id="btnSearch">
                            <i class="ace-icon glyphicon glyphicon-search"></i>
                            Tìm kiếm
                          </button>
                        </div>
                      </div>
                    </div>
                  </div>
                  <form:hidden path="id" id="buildingId"></form:hidden>
                </div>
              </form:form>

            </div>
          </div>
          <div class="pull-right">
            <div class="col-xs-12">
              <a href='<c:url value='/admin/building-edit'/>'>
                <button class="btn btn-white btn-info btn-bold" title="Thêm toà nhà" >
                  <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                       fill="currentColor" class="bi bi-building-add" viewBox="0 0 16 16">
                    <path
                            d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0">
                    </path>
                    <path
                            d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z">
                    </path>
                    <path
                            d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z">
                    </path>
                  </svg>
                </button>
              </a>
              <button class="btn btn-white btn-warning btn-bold" title="Xoá toàn bộ" id="btnDeleteAll"  onclick="warningBeforeDelete()">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                     fill="currentColor" class="bi bi-building-dash" viewBox="0 0 16 16">
                  <path
                          d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7M11 12h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1 0-1" />
                  <path
                          d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z" />
                  <path
                          d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z" />
                </svg>
              </button>
            </div>
          </div>
        </div>
      </div><!-- /.span -->

      <!-- bảng danh sách -->
      <div class="row">
        <div class="col-xs-12">
          <div class="table-responsive">
            <display:table name="modelSearch.listResult" cellspacing="0" cellpadding="0"
                           requestURI="${buildingListURL}" partialList="true" sort="external"
                           size="${modelSearch.totalItems}" defaultsort="2" defaultorder="ascending"
                           id="tableList" pagesize="${modelSearch.maxPageItems}"
                           export="false"
                           class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                           style="margin: 3em 0 1.5em;">
              <display:column title="<fieldset class='form-group'>
												        <input type='checkbox' id='checkAll' class='check-box-element'>
												        </fieldset>" class="center select-cell"
                              headerClass="center select-cell">
                <fieldset>
                  <input type="checkbox" name="checkList" value="${tableList.id}"
                         id="checkbox_${tableList.id}" class="check-box-element"/>
                </fieldset>
              </display:column>
              <display:column property="name" title="Tên toà nhà"/>
              <display:column property="address" title="Địa chỉ"/>
              <display:column property="numberOfBasement" title="Số tầng hầm"/>
              <display:column property="managerName" title="Tên quản lí"/>
              <display:column property="floorArea" title="Diện tích sàn"/>
              <display:column property="emptyArea" title="Diện tích trống"/>
              <display:column property="rentArea" title="Diện tích thuê"/>
              <display:column property="serviceFee" title="Phí dịch vụ"/>
              <display:column property="brokerageFee" title="Phí môi giới"/>
              <display:column title="Thao tác">

                <div class="hidden-sm hidden-xs btn-group">
                  <button class="btn btn-xs btn-success" title="Giao toà nhà" onclick="assignmentBuilding(${tableList.id})">
                    <i class="ace-icon glyphicon glyphicon-list"></i>
                  </button>

                  <a class="btn btn-xs btn-info" title="Sửa toà nhà"  href='<c:url value="/admin/building-edit-${tableList.id}"/>'>
                    <i class="ace-icon fa fa-pencil bigger-120"></i>
                  </a>

                  <button class="btn btn-xs btn-danger" title="Xoá toà nhà" id="btnDeleteBuilding" onclick="deleteBuilding(${tableList.id})">
                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
                  </button>
                </div>
              </display:column>
            </display:table>
          </div>
        </div>
      </div>
    </div>
  </div><!-- /.main-content -->


  <!-- giao toa nha -->
  <div class="modal fade" id="assignmentBuildingModal" role="dialog" style="font-family: 'Times New Roman', Times, serif;">
    <div class="modal-dialog">

      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title"></h4>
        </div>
        <div class="modal-body">
          <table class="table table-striped table-bordered table-hover" id="staffList">
            <thead>
            <tr>
              <th>Chọn</th>
              <th>Tên nhân viên</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
          </table>
          <input type="hidden" id="buildingId" name="buildingId" value="">
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" id="btnAssignmentBuilding">Giao toà nhà</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
        </div>
      </div>

    </div>
  </div>


  <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
    <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
  </a>
</div>
<script>
  function assignmentBuilding(buildingId){
    assignmentModalOpen();
    loadStaff(buildingId);
    $('#buildingId').val(buildingId);
    console.log($('#buildingId').val());
  }
  function assignmentModalOpen(){
    $('#assignmentBuildingModal').modal()
  }

  function loadStaff(buildingId){
    $.ajax({
      type: "GET",
      url: "${buildingAPI}/" + buildingId + "/staffs",
      // data: JSON.stringify(data),
      dataType: "json",
      // contentType: "application/json",
      success : function(response){
        var row = '';
        $.each(response.data, function (index,item) {
          row += '<tr>';
          row += '<td class="center"><input type="checkbox" value="' + item.id + '" id="checkbox_' + item.id + '" ' + item.checked + '/></td>';
          row += '<td class="text-center">' + item.fullName + '</td>';
          row += '</tr>';
        });
        $('#staffList tbody').html(row);
        console.log("success");
      },
      error: function(response){
        console.log("failed");
        console.log(response);
      }
    });
  }

  $('#btnAssignmentBuilding').click(function (e) {
    e.preventDefault();
    var data = {};
    data['buildingId'] = $('#buildingId').val();
    var staffs = $('#staffList').find('tbody input[type = checkbox]:checked').map(function(){
      return $(this).val();
    }).get();
    data['staffs'] = staffs;
    var urlLink = '${buildingAPIUser}/assignment?buildingId=' + encodeURIComponent(data.buildingId) +
            '&staffs=' + encodeURIComponent(data.staffs.join(','));
    $.ajax({
      type: "POST",
      url: urlLink,
      data: JSON.stringify(data),
      dataType: "json",
      contentType: "application/json",
      success: function (response) {
        console.log("Thành công");
        window.location.href = "<c:url value='/admin/building-list?message=success'/>";
      },
      error: function (response) {
        console.log("Thất bại");
        console.log(response);
        window.location.href = "<c:url value='/admin/building-list?message=error'/>";
      }
    });
  });
  $('#btnSearch').click(function (e) {
    e.preventDefault();
    $('#listForm').submit();
  });


  $('#btnDeleteBuilding').click(function (e){
    e.preventDefault();
    var buildingId = $('#buildingId').val();
    deleteBuilding(buildingId);
  });

  function deleteBuilding(buildingId){
    $.ajax({
      url: "${buildingAPI}/elimination/" + buildingId,
      type: "DELETE",
      dataType: "json",
      success: function (response) {
        console.log("Thành công");
        window.location.href = "<c:url value='/admin/building-list?message=success'/>";
      },
      error: function (response) {
        console.log("Thất bại");
        console.log(response);
        window.location.href = "<c:url value='/admin/building-list?message=error'/>";
      }
    });
  }
  function warningBeforeDelete() {
    showAlertBeforeDelete(function () {
      event.preventDefault();
      var dataArray = $('tbody input[type=checkbox]:checked').map(function () {
        return $(this).val();
      }).get();
      deleteAllBuilding(dataArray);
    });
  }

  function deleteAllBuilding(data) {
    $.ajax({
      url: '${buildingAPI}/eliminationAll',
      type: 'DELETE',
      dataType: 'json',
      contentType: 'application/json',
      data: JSON.stringify(data),
      success: function (response) {
        console.log("Thành công");
        window.location.href = "<c:url value='/admin/building-list?message=success'/>";
      },
      error: function (response) {
        console.log("Thất bại");
        console.log(response);
        window.location.href = "<c:url value='/admin/building-list?message=error'/>";
      }
    });
  }

</script>
</body>
</html>