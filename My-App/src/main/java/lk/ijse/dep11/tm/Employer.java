package lk.ijse.dep11.tm;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Data

public class Employer implements Serializable {
    private String id;
    private String name;
    private String contact;

}
