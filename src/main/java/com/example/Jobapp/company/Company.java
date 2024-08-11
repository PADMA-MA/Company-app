package com.example.Jobapp.company;


import com.example.Jobapp.job.Job;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @OneToMany
    private List<Job> jobs;

//    private List<Review> reviews;
}
