import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.algaworks.pedidovenda.model.Cliente;
import com.algaworks.pedidovenda.model.Endereco;
import com.algaworks.pedidovenda.model.TipoPessoa;

public class Teste {
	
	public static void main(String[] args) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("PedidoPU");
		EntityManager manager = factory.createEntityManager();
		
		EntityTransaction trx = manager.getTransaction();
		trx.begin();
		
		Cliente cliente = new Cliente();
		cliente.setNome("Leonardo Anselmo");
		cliente.setEmail("leonardo_anselmo@hotmail.com");
		cliente.setDocumentoReceitaFederal("031.309.094-78");
		cliente.setTipo(TipoPessoa.FISICA);	
		
		Endereco endereco = new Endereco();
		endereco.setLogradouro("R. Luiz da Silva Guerra");
		endereco.setNumero("13");
		endereco.setCidade("Garanhuns");
		endereco.setUf("PE");
		endereco.setCep("55296-510");
		endereco.setCliente(cliente);
		endereco.setComplemento("casa");
		
		cliente.getEnderecos().add(endereco);		
		
		manager.persist(cliente);
		
		trx.commit();
	}

}
