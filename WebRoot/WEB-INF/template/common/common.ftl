<#macro replaceBr str>
 <#assign x=str/>${x?replace('\r\n','<br>')}
</#macro>