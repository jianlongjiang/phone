<#escape x as x!"">
<#macro pageUtils total  currentIndex  className pageUrl replaceStr miniflag>
	<#assign start_i =1>
	<#assign end_i = total>
	<#if ((currentIndex-button_padding_unit) gt 0)>
	<#assign start_i =currentIndex-button_padding_unit>
	</#if>
	<#if (max_distance+start_i) lte total>
	<#assign end_i = (max_distance+start_i)-1>
   	</#if>     	
   	<#if (start_i+max_distance-1) gt end_i>
   	<#assign start_i =end_i-max_distance+1>
   	</#if>   	
   	<#if start_i lte 0>
   	<#assign start_i = 1>
   	</#if>
   	<#if currentIndex==1>
   	<li class="prev disabled"><a href="javascript:void(0)"><i class="icon-double-angle-left"></i></a></li>
   	<#else>
   	<li class="prev" ><a href="${pageUrl?replace(replaceStr, (currentIndex-1)?string)}"><i class="icon-double-angle-left"></i></a></li>
   	</#if>
   	<#if (start_i-1) gt 0>
		<li class=""><a  href='${pageUrl?replace(replaceStr, (start_i-1)?string)}'>...</a></li>
   	</#if>
	<#list start_i ..end_i as i>	
		<#if i == currentIndex>
			<li class="active"><a href='${pageUrl?replace(replaceStr, i?string)}'>${i}</a></li>
			<#else>
			<li class=""><a  href='${pageUrl?replace(replaceStr,i?string)}'>${i}</a></li>
		</#if>
	</#list>
 	<#if (end_i+1) lt total >
		<li class="page-wrap"><a hrefdiv${pageUrl?replace(replaceStr, (end_i+1)?string)}'>...</a></li>
   	</#if> 
   	<#if currentIndex==total>
   	<li class="next disabled"><a href="javascript:void(0)"><i class="icon-double-angle-right"></i></a></li>
   	<#else>
   	<li class="next"><a href="${pageUrl?replace(replaceStr, (currentIndex+1)?string)}"><i class="icon-double-angle-right"></i></a></li>
   	</#if>
</#macro>

	<#if page??>
		<#assign pages_page_url = pages_page_url?default( pageUrl )>
		
	<#if !pages_current_page?exists>
		<#if page??>
			<#assign pages_current_page = pages_current_page?default(page.page )>
		</#if>
	</#if>
	
	<#if !pages_total_page?exists>
		<#if page??>
			<#assign pages_total_page = pages_total_page?default( page.totalPages )>
		</#if>
	</#if>

	<#--<#assign pages_previous_page = pages_current_page - 1>
	<#assign pages_next_page = pages_current_page + 1> -->
	
	<#if pages_total_page lte 0>
	<#assign pages_total_page =1>
	</#if> 
	<#if pages_current_page gt pages_total_page>
	<#assign pages_current_page = pages_total_page>	
	</#if>
	
	<#if !pageMiniflag?exists>
	     <#assign pageMiniflag = 1>
		 <#assign button_padding_unit = 3>
		 <#assign max_distance = 6>
    <#else>
         <#assign button_padding_unit = 2>
		 <#assign max_distance = 3>
	</#if>
	
	<@pageUtils total=pages_total_page  currentIndex=pages_current_page  className="current" pageUrl=pages_page_url replaceStr="%23" miniflag=pageMiniflag/>
	
</#if>
</#escape>