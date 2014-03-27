package com.cmware.dw.example.representations;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class containing information on the statement to render.
 */
public class HelloStatement
{
    private long id;
    private String content;

    /**
     * For Jackson de-serialization.  Do not use typically.
     */
    public HelloStatement()
    {
    }

    public HelloStatement(long id, String content)
    {
        this.id = id;
        this.content = content;
    }

    @JsonProperty
    public long getId()
    {
        return id;
    }

    @JsonProperty
    public String getContent()
    {
        return content;
    }
}
