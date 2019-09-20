<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>

<!-- Content -->
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #fff">
        <form id="searchArtifactIdsForm">
            <table>
                <tr>
                    <th>哈希值：</th>
                    <td><input class="easyui-textbox" name="hash"/>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" onclick="searchArtifactIdsFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-remove-circle',plain:true" onclick="cleanArtifactIdsFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <!-- data list -->
    <div data-options="region:'center',border:true,title:'商品编码列表'">
        <table id="artifactIdsDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>

<script>
    var artifactIdsDataGrid ;
    $(function () {
        // 加载商品编码列表数据
        getArtifactIdsListData();

    });

    // 初始化商品编码列表数据
    function getArtifactIdsListData(){
        artifactIdsDataGrid = $('#artifactIdsDataGrid').datagrid({
            width: 'auto',
            height: 'auto',
            pageSize: 10,
            striped: true,
            singleSelect: true,
            url: projectPath + 'getArtifactIdsList',
            loadMsg: '数据加载中请稍后...',
            pagination: true,
            fitColumns: true,
            fit: true,
            remoteSort: false,
            columns: [[{
                field: 'id',
                align: 'center',
                width: 50,
                hidden: true
            }, {
                field: 'type',
                title: '商品类型',
                align: 'center',
                width: 20
            }, {
                field: 'hash',
                title: '哈希值',
                align: 'center',
                width: 100
            }, {
                field: 'state',
                title: '状态标记',
                align: 'center',
                width: 20,
                formatter: function (value, rec) {
                    if (value == 0) {
                        return '<span style="color:green">未使用</span>';
                    } else if (value == 1) {
                        return '<span style="color:blue">被下载</span>';
                    } else if (value == 2) {
                        return '<span style="color:aqua">已校验</span>';
                    } else {
                        return value;
                    }
                }
            }, {
                field: 'appid',
                title: '应用id',
                align: 'center',
                sortable: true,
                width: 20
            }, {
                field: 'userid',
                title: '用户id',
                align: 'center',
                sortable: true,
                width: 20
            }, {
                field: 'modifyTime',
                title: '修改时间',
                align: 'center',
                sortable: true,
                width: 30
            }, {
                field: 'createTime',
                title: '创建时间',
                align: 'center',
                sortable: true,
                width: 30
            }]],
            onLoadError: function () {

            }
        });
    }

    // 查询
    function searchArtifactIdsFun() {
        artifactIdsDataGrid.datagrid('load', $.serializeObject($('#searchArtifactIdsForm')));
    }
    // 清空
    function cleanArtifactIdsFun() {
        $('#searchArtifactIdsForm input').val('');
        $("#searchArtifactIdsForm").form('clear');
        artifactIdsDataGrid.datagrid('load', {});
    }
</script>
