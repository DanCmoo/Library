package com.dancmo.project.libraryud.services.implementation;

import com.dancmo.project.libraryud.persistence.entity.Autor;
import com.dancmo.project.libraryud.persistence.entity.Categoria;
import com.dancmo.project.libraryud.persistence.entity.Libro;
import com.dancmo.project.libraryud.persistence.repository.AutorRepository;
import com.dancmo.project.libraryud.persistence.repository.CategoriaRepository;
import com.dancmo.project.libraryud.persistence.repository.LibroRepository;
import com.dancmo.project.libraryud.presentation.dto.ApiExtDTO;
import com.dancmo.project.libraryud.presentation.dto.DocumentDTO;
import com.dancmo.project.libraryud.presentation.dto.LibroResponseDTO;
import com.dancmo.project.libraryud.services.LibroService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LibroServiceImpl implements LibroService {

    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;
    private final CategoriaRepository categoriaRepository;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public List<LibroResponseDTO> getLibroByCategory(String category) {
        List<Libro> l = libroRepository.findAllByCategoria_NombreCategoria(category);
        List<LibroResponseDTO> dtos = new ArrayList<>();
        if (!l.isEmpty()) {
            for(Libro libro : l){
                LibroResponseDTO dto = this.fromLibroEntityToDTO(libro);
                dtos.add(dto);
            }
        } else {
            StringBuilder urlS = new StringBuilder("https://openlibrary.org/search.json");
            urlS.append("?subject=" + category);
            urlS.append("&fields=title,first_publish_year,author_name,subject");
            urlS.append("&limit=50");
            try{
                ApiExtDTO jsonResponse = restTemplate.getForObject(urlS.toString(), ApiExtDTO.class);
                if(jsonResponse.getNumFound()!=0) {
                    for (DocumentDTO doc : jsonResponse.getDocs()) {
                        Libro bookTemp = new Libro();
                        Autor autorTemp = new Autor();
                        List<Autor> autorTempList = autorRepository.findAllByNombre(doc.getAuthor_name().get(0));
                        Categoria categoriaTemp = new Categoria();
                        List<Categoria> categoriaTempList = categoriaRepository.findAllByNombreCategoria(doc.getSubject().get(0));

                        bookTemp.setTitulo(doc.getTitle());
                        bookTemp.setAnioPublicacion(doc.getFirst_publish_year());
                        bookTemp.setDisponibilidad(true);

                        if(autorTempList.isEmpty()) {
                            autorTemp.setNombre(doc.getAuthor_name().get(0));
                            autorTemp.setPaisOrigen("Not provided.");
                            autorRepository.save(autorTemp);
                            bookTemp.setAutor(autorTemp);
                        }else{
                            bookTemp.setAutor(autorTempList.get(0));
                        }

                        if(categoriaTempList.isEmpty()) {
                            categoriaTemp.setNombreCategoria(doc.getSubject().get(0));
                            categoriaTemp.setDescripcion("Not provided.");
                            categoriaRepository.save(categoriaTemp);
                            bookTemp.setCategoria(categoriaTemp);
                        }else{
                            bookTemp.setCategoria(categoriaTempList.get(0));
                        }
                        libroRepository.save(bookTemp);
                        category =doc.getAuthor_name().get(0);

                        LibroResponseDTO dto = this.fromLibroEntityToDTO(bookTemp);
                        dtos.add(dto);
                    }
                }else{
                    return null;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return dtos;
    }

    @Override
    public List<LibroResponseDTO> getLibroByAutor(String autor) {
        List<Libro> l = libroRepository.findAllByAutor_Nombre(autor);
        List<LibroResponseDTO> dtos = new ArrayList<>();
        if (!l.isEmpty()) {
            for(Libro libro : l){
                LibroResponseDTO dto = this.fromLibroEntityToDTO(libro);
                dtos.add(dto);
            }
        } else {
            StringBuilder urlS = new StringBuilder("https://openlibrary.org/search.json");
            urlS.append("?author=" + autor);
            urlS.append("&fields=title,first_publish_year,author_name,subject");
            urlS.append("&limit=50");
            try{
                ApiExtDTO jsonResponse = restTemplate.getForObject(urlS.toString(), ApiExtDTO.class);
                if(jsonResponse.getNumFound()!=0) {
                    for (DocumentDTO doc : jsonResponse.getDocs()) {
                        Libro bookTemp = new Libro();
                        Autor autorTemp = new Autor();
                        List<Autor> autorTempList = autorRepository.findAllByNombre(doc.getAuthor_name().get(0));
                        Categoria categoriaTemp = new Categoria();
                        List<Categoria> categoriaTempList = categoriaRepository.findAllByNombreCategoria(doc.getSubject().get(0));

                        bookTemp.setTitulo(doc.getTitle());
                        bookTemp.setAnioPublicacion(doc.getFirst_publish_year());
                        bookTemp.setDisponibilidad(true);

                        if(autorTempList.isEmpty()) {
                            autorTemp.setNombre(doc.getAuthor_name().get(0));
                            autorTemp.setPaisOrigen("Not provided.");
                            autorRepository.save(autorTemp);
                            bookTemp.setAutor(autorTemp);
                        }else{
                            bookTemp.setAutor(autorTempList.get(0));
                        }

                        if(categoriaTempList.isEmpty()) {
                            categoriaTemp.setNombreCategoria(doc.getSubject().get(0));
                            categoriaTemp.setDescripcion("Not provided.");
                            categoriaRepository.save(categoriaTemp);
                            bookTemp.setCategoria(categoriaTemp);
                        }else{
                            bookTemp.setCategoria(categoriaTempList.get(0));
                        }
                        libroRepository.save(bookTemp);
                        autor =doc.getAuthor_name().get(0);

                        LibroResponseDTO dto = this.fromLibroEntityToDTO(bookTemp);
                        dtos.add(dto);
                    }
                }else{
                    return null;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return dtos;
    }

    @Override
    public LibroResponseDTO fromLibroEntityToDTO(Libro book) {
        LibroResponseDTO dto = new LibroResponseDTO();
        dto.setTitulo(book.getTitulo());
        dto.setAnioPublicacion(book.getAnioPublicacion());
        dto.setDisponibilidad(book.isDisponibilidad());
        dto.setAutor(book.getAutor().getNombre());
        dto.setCategoria(book.getCategoria().getNombreCategoria());
        return dto;
    }
}
