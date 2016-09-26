package easyFrame.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Menu {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String body;
    	
	
	

	
}
