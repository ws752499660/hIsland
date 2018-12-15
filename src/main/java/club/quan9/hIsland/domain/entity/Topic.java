package club.quan9.hIsland.domain.entity;

import java.util.Date;

public class Topic
{
    private String id;
    private String title;
    private String authorId;
    private String authorName;
    private Date date;
    private String content;

    public String getAuthorName()
    {
        return authorName;
    }

    public void setAuthorName(String authorName)
    {
        this.authorName = authorName;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getAuthorId()
    {
        return authorId;
    }

    public void setAuthorId(String authorId)
    {
        this.authorId = authorId;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }
}
