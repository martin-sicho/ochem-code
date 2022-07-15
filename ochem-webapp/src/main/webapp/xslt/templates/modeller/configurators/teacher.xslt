<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:include href="configurators-common.xslt" />
	
	<xsl:template name="configuration-content">
		<title>Model builder - Teaching</title>
		<h1>Run model builder</h1>
		<div id="process">
			<img src="img/roller.gif" id="progress-img"/><br/>
			<span id="status">Initializing...</span><br/>
			<a href="javascript:killTask()">[cancel]</a>
			<a href="modelconfigurator/configure.do?page=later">[fetch result later]</a>
		</div>
		<script language="javascript">
			var ajax = new QSPR.Ajax();
			var timer = setInterval("checkStatus()", 3000);
			$("#status").html('Starting...');
			
			$(document).ready(function()
			{
				$("[name='next']").attr("disabled", "disabled");
			});
			
			function checkStatus()
			{
				ajax.url = 'modelconfigurator/status.do';
				ajax.send({
					data:'',
					success: function(xml)
					{
						var status = xml.message.message;
						$("#status").html(status);
						if (status == "Finished")
						{
							$("[name='next']").removeAttr("disabled");		
							clearInterval(timer);	
											
							// To delete browser history entry
							window.location.replace(webRoot + "modelconfigurator/submit.do?page=teach&amp;render-mode=popup");
							//document.getElementById("wizardform").submit();
						}
						else if (status.substring(0, 5) == "Error")
						{
							$("#progress-img").attr('src', 'img/icons/error.jpg');		
							clearInterval(timer);	
						}
					},
					error: function(msg)
					{
						$("#status").html('QSPR server is not available');
					}
				});
			}
			
			function killTask()
			{
				ajax.url = 'modelconfigurator/kill.do';
				ajax.send({
					data:'',
					success: function(xml)
					{
						window.alert("Task has been successfully killed");
					}
				});	
			}
		</script>
	</xsl:template>
</xsl:stylesheet>