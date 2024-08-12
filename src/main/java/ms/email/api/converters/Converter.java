package ms.email.api.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class Converter {

  private ModelMapper modelMapper;

  /**
   * @param <E>
   * @param <R>
   * @param source
   * @param targetClass
   * @return
   */

  public <E, R> R map(E source, Class<R> targetClass) {
    return modelMapper.map(source, targetClass);
  }

  /**
   * Converte uma lista de Entidades para uma lista de Responses
   *
   * @param <E>
   * @param <R>
   * @param sourceList
   * @param targetClass
   * @return
   */
  public <E, R> List<R> mapCollection(List<E> sourceList, Class<R> targetClass) {
    return sourceList.stream()
        .map(source -> map(source, targetClass))
        .collect(Collectors.toList());
  }

  /**
   * Converte uma Page<Entidade> para Page<Response>
   *
   * @param <E>
   * @param <R>
   * @param sourcePage
   * @param targetClass
   * @return
   */
  public <E, R> Page<R> mapPage(Page<E> sourcePage, Class<R> targetClass) {
    return sourcePage.map(source -> map(source, targetClass));
  }

}
