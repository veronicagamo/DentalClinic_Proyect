<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   <html>
	   <head>
<style>
body {
  background-color: lightgreen;
}

h1 {
  color: darkgreen;
  text-align: center;
}

p {
  font-family: verdana;
  font-size: 20px;
}
</style>
</head>
<body>
	<h1>SUPPLIER DATA</h1>
   <p><b>Supplier:</b></p>
   <p><b>Name: </b> <xsl:value-of select="Supplier/sup_name" /> </p>
   <p><b>Email: </b><xsl:value-of select="Supplier/email" /> </p>
   <p><b>Address: </b><xsl:value-of select="Supplier/sup_address" /> </p>
   </body>
  </html>
</xsl:template>
</xsl:stylesheet>