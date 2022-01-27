package com.platzi.market.persistence.mapper;

import com.platzi.market.domain.Product;
import com.platzi.market.persistence.entity.Producto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {
    //creamos mapeadores
    //source: fuente - target: destino
    //como estamos utilizando aqui category y category tiene su mapper propio, dentro de la anotación @Mapper vamos a incluir un nuevo parámetro, el ya sabe internamente que cuando vaya a convertir categoria dentro de category, tiene que usar CategoryMapper
    @Mappings({
            @Mapping(source = "idProducto", target = "productId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "precioVenta", target = "price"),
            @Mapping(source = "cantidadStock", target = "stock"),
            @Mapping(source = "estado", target = "active"),
            @Mapping(source = "categoria", target = "category")
    })

    Product toProduct(Producto producto);
    //va a recibir una lista de productos
List<Product> toProducts(List<Producto> productos);

//conversion contraria, recibimos un Product, esta conversion toma el mapeo de manera inversa, osea, source va a ser target y target va a ser source, pero como no tenemos en cuenta el código de barras, que tenemos en la clase Producto, debemos ignorarlo.
    @InheritInverseConfiguration
    @Mapping(target = "codigoBarras", ignore = true)
    Producto toProducto(Product product);

}
