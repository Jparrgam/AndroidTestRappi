package co.com.mobiletest.view.model;

import java.io.Serializable;

import se.emilsjolander.sprinkles.Model;
import se.emilsjolander.sprinkles.annotations.AutoIncrement;
import se.emilsjolander.sprinkles.annotations.Column;
import se.emilsjolander.sprinkles.annotations.Key;
import se.emilsjolander.sprinkles.annotations.Table;

/**
 * @author Jaime Gamboa
 * @see se.emilsjolander.sprinkles.Model
 */
@Table("Reddits")
public class RedditsModelEntity extends Model implements Serializable {

    @Key
    @AutoIncrement
    @Column("idReddit")
    private long idReddit;

    @Column("banner_img")
    public String banner_img;

    @Column("submit_text_html")

    public String submit_text_html;

    @Column("submit_text")
    public String submit_text;

    @Column("display_name")
    public String display_name;

    @Column("header_img")
    public String header_img;

    @Column("description_html")
    public String description_html;

    @Column("public_description_html")
    public String public_description_html;

    @Column("public_description")
    public String public_description;

    public long getId() {
        return idReddit;
    }

    public RedditsModelEntity(long idReddit, String banner_img, String submit_text_html, String submit_text, String display_name, String header_img, String description_html, String public_description_html, String public_description) {
        this.idReddit = idReddit;
        this.banner_img = banner_img;
        this.submit_text_html = submit_text_html;
        this.submit_text = submit_text;
        this.display_name = display_name;
        this.header_img = header_img;
        this.description_html = description_html;
        this.public_description_html = public_description_html;
        this.public_description = public_description;
    }

    public RedditsModelEntity() {}
}
