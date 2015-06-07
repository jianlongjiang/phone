<#escape x as x!"">
<#-- 搜索URL {pageNo} -->
<#macro searchPageUrl url values='' query=''>
<#if query != ''>
${URLUtils.searchUrl(url + "?" + query, values, false)}<#t>
<#else>
${URLUtils.searchUrl(url, values, false)}<#t>
</#if>
</#macro>
</#escape>

<#-- 搜索URL   ?xx&pageNo=1  -->
<#macro searchUrl url values='' query='' encodeFlag=false>
<#if query != ''>
${URLUtils.searchUrl(url + "?" + query, values+"&pageNo=1", encodeFlag)}<#t>
<#else>
${URLUtils.searchUrl(url, values+"&pageNo=1", encodeFlag)}<#t>
</#if>
</#macro>
<#macro getDays from>
${URLUtils.getDays(from)}
</#macro>

<#macro loadRoleName managerId>
${(URLUtils.getRole(managerId).nameCh)!''}
</#macro>

<#macro loadUserGroupMount userId>
${(URLUtils.loadUserGroupMount(userId))!''}
</#macro>
<#macro searchMobileName mobile>
${(URLUtils.searchMobileName(mobile))!''}
</#macro>
<#macro searchDownloadAmount mobile>
${(URLUtils.searchDownloadAmount(mobile))!''}
</#macro>
<#macro getRedPackCount id>
${(URLUtils.getRedPackCount(id))!''}
</#macro>

