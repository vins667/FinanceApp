
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
<!--[if IE]>
            <style type="text/css">
                .div1 {
                    position: relative;
                    overflow-y: scroll;
                    overflow-x: hidden;
                    border: solid #006699;
                    border-width: 0px 0px 0px 0px;
                    padding-top: 30px ;
        
                }
                .tr1 {
                     position:absolute;
                     top: expression(this.offsetParent.scrollTop);                     
                  }
                tbody {
                    height: auto;
                }
                tfoot{
                    background:#3383bb;
                    font-weight:bold;
                }
                .tr2 {
                     position:absolute;
                     bottom:expression(this.offsetParent.scrollTop);
                  }
            </style>
        <![endif]-->
</head>
<body>

	<div class="div1"
		style="width: 100.0%; overflow: auto; height: 320.0pt; border-width: 1pt; border-color: whitesmoke; border-style: none">
		<table border="0" align="center" bgcolor="DarkSalmon" cellspacing="1"
			cellpadding="2" width="100%" id="tablea">
			<tr class="tr1">
				<th style="font-size: 8pt" align="left">Sl#</th>
				<th style="font-size: 8pt" align="left" height="25pt">CO No</th>
				<th style="font-size: 8pt" align="left" height="25pt">Line</th>
				<th style="font-size: 8pt" align="left">Item No</th>
				<th style="font-size: 8pt" align="left">Buyer PO</th>
				<th style="font-size: 8pt" align="left">Buyer Style</th>
				<th style="font-size: 8pt" align="left">Unit</th>
				<th style="font-size: 8pt" align="left">Inv Desc</th>
				<th style="font-size: 8pt" align="right">Qnty</th>
				<th style="font-size: 8pt" align="left">Price FC</th>
				<th style="font-size: 8pt" align="left">FOB FC</th>
				<th style="font-size: 8pt" align="left">HSN Code</th>
				<th style="font-size: 8pt" align="left">IGST %</th>
				<th style="font-size: 8pt" align="left">SGST %</th>
				<th style="font-size: 8pt" align="left">CGST %</th>

			</tr>
			<tbody>
				<s:set id="TOT_QTY_ENDORS" name="TOT_QTY_ENDORS" value="0" />
				<s:set id="TOT_FOB" name="TOT_FOB" value="0" />
				<s:iterator value="InvLineList" status="listid">
					<tr bgcolor="#f2f9fb">
						<td style="font-size: 8pt"><s:property
								value="%{#listid.index+1}" /></td>

						<td style="font-size: 8pt"><s:property value="CO_NO" /></td>
						<td style="font-size: 8pt"><s:property value="CO_LINE" /></td>
						<td style="font-size: 8pt"><s:property value="ITEM_NO" /></td>
						<td style="font-size: 8pt"><s:property value="PRE_PRINT_NO" /></td>
						<td style="font-size: 8pt"><s:property value="TOKEN_NO" /></td>
						<td style="font-size: 8pt"><s:property value="UNIT" /></td>
						<td style="font-size: 8pt"><s:property value="INV_DESC" /></td>
						<td style="font-size: 8pt" align="right"><s:property
								value="QTY_ENDORS" /> <s:set id="TOT_QTY_ENDORS"
								name="TOT_QTY_ENDORS" value="%{#TOT_QTY_ENDORS+QTY_ENDORS}" /></td>
						<td style="font-size: 8pt" align="right"><s:property
								value="PRICE_FC" /></td>
						<td style="font-size: 8pt" align="right"><s:property
								value="FOB_FC" /> <s:set id="TOT_FOB" name="TOT_FOB"
								value="%{#TOT_FOB+FOB_FC}" /></td>

						<td style="font-size: 8pt" align="left"><s:property
								value="HSN_CODE" /></td>
						<td style="font-size: 8pt" align="right"><s:property
								value="IGST_PER" /></td>
						<td style="font-size: 8pt" align="right"><s:property
								value="CGST_PER" /></td>
						<td style="font-size: 8pt" align="right"><s:property
								value="SGST_PER" /></td>
						<s:hidden name="CO_NO_EXCISE" value="%{CO_NO}" />
						<s:hidden name="CO_LINE_EXCISE" value="%{CO_LINE}" />
					</tr>
				</s:iterator>

			</tbody>
			<tr bgcolor="#f2f9fb">
				<td style="font-size: 8pt" colspan="8" align="right" class="label-1">Total</td>
				<td style="font-size: 8pt; color: #9400D3; font-weight: bold;"
					align="right"><s:property value="%{#TOT_QTY_ENDORS}" /></td>
				<td style="font-size: 8pt; color: #9400D3; font-weight: bold;"
					align="right"></td>
				<td style="font-size: 8pt; color: #9400D3; font-weight: bold;"
					align="right"><s:property value="%{#TOT_FOB}" /></td>
				<td style="font-size: 8pt; color: #9400D3; font-weight: bold;"
					align="right"></td>
				<td style="font-size: 8pt; color: #9400D3; font-weight: bold;"
					align="right"></td>
				<td style="font-size: 8pt; color: #9400D3; font-weight: bold;"
					align="right"></td>
				<td style="font-size: 8pt; color: #9400D3; font-weight: bold;"
					align="right"></td>
			</tr>
		</table>
	</div>

</body>
</html>
