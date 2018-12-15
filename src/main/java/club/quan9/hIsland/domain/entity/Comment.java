package club.quan9.hIsland.domain.entity;

import java.util.Date;

public class Comment
{
    private String id;
    private String topicId;
    private String authorId;
    private String authorName;
    private String content;
    private Date date;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getAuthorName()
    {
        return authorName;
    }

    public void setAuthorName(String authorName)
    {
        this.authorName = authorName;
    }

    public String getTopicId()
    {
        return topicId;
    }

    public void setTopicId(String topicId)
    {
        this.topicId = topicId;
    }

    public String getAuthorId()
    {
        return authorId;
    }

    public void setAuthorId(String authorId)
    {
        this.authorId = authorId;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }
}
