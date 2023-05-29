<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   <html>
	   	   <head>
<style>
body {
  background-color: lightblue;
}

h1 {
  color: white;
  text-align: center;
}

p {
  font-family: verdana;
  font-size: 20px;
}
</style>
</head>
<body>
	<h1>CLIENT DATA</h1>
   <p><b>Name: </b> <xsl:value-of select="Client/pat_name" /></p>
   <p><b>Email: </b> <xsl:value-of select="Client/pat_email" /> </p>
   <p><b>Health number: </b> <xsl:value-of select="Client/hum" /> </p>
   <p><b>Address: </b> <xsl:value-of select="Client/pat_address" /> </p>
   </body>
  </html>
</xsl:template>
</xsl:stylesheet>