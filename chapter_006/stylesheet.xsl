<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xls="http://www.w3.org/1999/XSL/Transform">
    <xls:output method="xml" />

    <xsl:template match="entries">
        <xsl:element name="entries">
            <xsl:apply-templates/>
        </xsl:element>
    </xsl:template>

    <xsl:template match="/entries/entry">
        <xsl:element name="entry">
            <xsl:attribute name="field"><xsl:value-of select="field"/></xsl:attribute>
        </xsl:element>
    </xsl:template>

</xsl:stylesheet>