<#escape x as x!"">
<#include "../common/header.ftl">
<script language="javascript" type="text/javascript" src="${request.contextPath}/resources/admin/js/datepicker/WdatePicker.js"></script>
<div class="main-content">
    <div class="breadcrumbs" id="breadcrumbs">


        <ul class="breadcrumb">
            <li>
                <i class="icon-home home-icon"></i>
                <a href="#">统计页面</a>
            </li>
        </ul><!-- .breadcrumb -->

    </div>

    <div class="page-content">
        <div class="page-header">
            <form class="form-inline" action="${request.contextPath}/admin/statistics/vipList/p1/">
                <h3>蜗牛巴巴会员统计</h3>
                <div class="form-group" style="margin-bottom: 10px;">
                    <label>统计时间：</label>
                    <input class="input-large Wdate" type="text" name="startDate" value="<#if (bean.startDate)??>${bean.startDate?string('yyyy-MM-dd')}</#if>"  onFocus="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" >
                    -
                     <input class="input-large Wdate" type="text" name="endDate" value="<#if (bean.endDate)??>${bean.endDate?string('yyyy-MM-dd')}</#if>"  onFocus="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" >
                </div>
                <br>
                <label>金蜗牛邀请人数最多前：</label>
                <input type="text" value="${bean.goldenNum}"  id="goldenNum" name="goldenNum"/>名&nbsp;&nbsp;&nbsp;&nbsp;
                <label>邀请好友人数最多前：</label>
                <input type="text" value="${bean.inviteNum}"  id="inviteNum" name="inviteNum"/>名&nbsp;&nbsp;&nbsp;&nbsp;
                <label>赚到钱数最多前：</label>
                <input type="text" value="${bean.maxMoney}"  id="maxMoney" name="maxMoney"/>名&nbsp;&nbsp;&nbsp;&nbsp;
                <br/>
				<button class="btn btn-sm btn-primary">
                 <i class="icon-search align-top"></i>查询
                </button> 
            </form>
        </div><!-- /.page-header -->

        <div class="row">
            <div class="col-xs-12">
                <!-- PAGE CONTENT BEGINS -->
                <div class="row">
                    <div class="tabbable ">
                        <div class="tab-content">
                            <div class="table-responsive">
                                <div class="dataTables_wrapper">
                                    <table id="J_Table" class="table table-striped table-bordered table-hover J_Table">
                                        <thead>
                                            <tr>
                                    <th>ID</th>
                                    <th>姓名</th>
                                    <th>微信</th>
                                    <th>手机号</th>
                                    <th>级别</th>
                                    <th>注册时间</th>
                                    <th>邀请好友数</th>    
                                    <th>邀请到金蜗牛会员数</th>
                                    <th>下载数量</th>
                                    <th>团队人数</th>
                                    <th>优先级</th>
                                    <th>经验值</th>
                                    <th>收到的红包金额</th>
                                    <th>提现金额</th>
                                    <th>账户余额</th>
                                    <th>积分</th>
                                                
                                            </tr>
                                        </thead>

                                        <tbody>
                                        		<#if infos?has_content>
                                <#list infos as info>
                                <tr> 
                                    <td>${info.fakeId}</td>
                                    <td>${info.realName}</td>
                                    <td>${info.weixin}</td>
                                    <td>${info.mobile}</td>
                                    <td>
                                    <#if levels?has_content>
                                    <#list levels as l>
                                      <#if l.id?? && info.userLevelId?? && l.id=info.userLevelId>
                                      ${l.levelName}
                                      </#if>
                                    </#list>
                                    </#if>
                                    </td>
                                    
                                    <td><#if info.createTime?has_content>${info.createTime?string("yyyy-MM-dd")}</#if></td>
                                    <td>${info.inviteFriendAmount}</td>                     
                                    <td>${info.inviteFriendVipAmount}</td>
                                    <td>${info.downloadMobileAmount}</td>
                                    <td> <@lh.loadUserGroupMount info.id/></td> 
                                    <td>${info.downloadLevel}</td>
                                    <td>${info.experience}</td> 
                                    <td><@lh.getRedPackCount info.id/></td>
                                    <td>${info.reflectRed}</td>
                                    <td>${info.balance}</td>
                                    <td>${info.integration}</td>                              
                                </#list>
                                </#if>
                                        </tbody>
                                    </table>
                                    <div class="row">
                                        <div class="col-xs-6">

                                        </div>
                                        <div class="col-xs-6">
												 <ul class="pagination">
                                            <#if page?has_content>
													<#assign pageUrl>
														<@lh.searchPageUrl request.requestUri 'pageNo=#' request.queryString/>
													</#assign>
													<#include "../common/pages.ftl">
													</#if>
                                      	  </ul>
                                        </div>
                                    </div>
                                </div>
                            </div><!-- /.table-responsive -->
                        </div>
                    </div>
                </div><!-- /.row -->

                <div class="hr hr32 hr-dotted"></div>

                <!-- PAGE CONTENT ENDS -->
            </div><!-- /.col -->
        </div><!-- /.row -->


        <div class="page-footer">

        </div><!-- /.page-header -->

    </div><!-- /.page-content -->
</div><!-- /.main-content -->
<#include "../common/footer.ftl">
<script type="text/javascript">
    $(function(){
      $("#goldenNum").focus(function(){
          $("#inviteNum").val("");
          $("#maxMoney").val("");
      });
      $("#inviteNum").focus(function(){
          $("#goldenNum").val("");
          $("#maxMoney").val("");
      });
      $("#maxMoney").focus(function(){
          $("#inviteNum").val("");
          $("#goldenNum").val("");
      });
    });
</script>
</#escape>