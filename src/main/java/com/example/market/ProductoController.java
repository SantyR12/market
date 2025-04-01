package com.example.market;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/productos")
public class ProductoController {
        
    @Autowired
    private ProductoRepositorty productoRespository;

    @Autowired
    private ProductoMapper productoMapper;

    @GetMapping
    public List<ProductoDTO> obtenerProductos() {
        return productoRespository.findAll()
        .stream().map(productoMapper::toProductoDTO)
        .collect(Collectors.toList()); 
    }
    @PostMapping
    public ProductoDTO crearProducto(@RequestBody ProductoDTO productoDTO) {
        return productoMapper.toProductoDTO(productoRespository.save(productoMapper.toProducto(productoDTO)));
    }
    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable Long id) {
        productoRespository.deleteById(id);
    }



}
