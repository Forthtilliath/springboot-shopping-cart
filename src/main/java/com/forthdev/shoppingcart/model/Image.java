package com.forthdev.shoppingcart.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;

@Getter
@Setter
@Entity(name = "images")
@AllArgsConstructor
@NoArgsConstructor
public class Image
{
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	private String filename;

	private String filetype;

	@Lob
	private Blob image;

	private String download_url;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
}
