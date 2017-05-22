package br.com.snowbine.base.converter;

import java.io.Serializable;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.snowbine.base.entity.BaseEntity;

@FacesConverter("generic")
public class GenericConverter implements Converter, Serializable
{
	private static final long serialVersionUID = -6899135169330649043L;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value)
	{
		if (value != null)
		{
			return this.getAttributesFrom(component).get(value);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value)
	{
		if (value != null && !"".equals(value))
		{
			BaseEntity entity = (BaseEntity) value;

			// adiciona item como atributo do componente
			this.addAttribute(component, entity);

			Integer codigo = entity.getId();
			
			if (codigo != null)
			{
				return String.valueOf(codigo);
			}
		}

		return (String) value;
	}
	
	protected void addAttribute(UIComponent component, BaseEntity o) 
	{
        String key = o.getId().toString(); 
        this.getAttributesFrom(component).put(key, o);
    }

    protected Map<String, Object> getAttributesFrom(UIComponent component) 
    {
        return component.getAttributes();
    }

}
