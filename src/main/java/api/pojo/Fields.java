package api.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Fields {
    public Project project;
    public String summary;
    public String description;
    public Issuetype issuetype;
}
