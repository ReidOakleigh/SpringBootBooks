package dmacc.springbootbooks.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Rumbi
 * Date: 3/22/21
 * Time: 10:29 PM
 */
@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String author;
    private String publisher;
    private String pubDate;
}
