package org.com.cqrsmonolith.command.commandController;

import org.com.cqrsmonolith.command.commandService.CommandService;
import org.com.cqrsmonolith.domain.Product;
import org.com.cqrsmonolith.dto.ProductDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class CommandController {

    private final CommandService commandService;

    public CommandController(CommandService commandService) {
        this.commandService = commandService;
    }

    @PostMapping
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO){
        return commandService.create(productDTO);
    }

    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable long id, @RequestBody ProductDTO productDTO){
        ProductDTO newDTO = new ProductDTO(id,
                productDTO.name(),
                productDTO.description(),
                productDTO.price());
        return commandService.update(newDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        commandService.delete(id);
    }
}
