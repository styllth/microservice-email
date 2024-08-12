package ms.email.domain.enums;

public enum PostGraduationEnum {

	AE("Almirante de Esquadra"),
	VA("Vice-Almirante"),
	CA("Contra-Almirante"),
	CMG("Capitão de Mar e Guerra"),
	CF("Capitão de Fragata"),
	CC("Capitão de Corveta"),
	CT("Capitão-Tenente"),
	_1T("1º Tenente"),
	_2T("2º Tenente"),
	GM("Guarda Marinha"),
	SO("Suboficial"),
	_1SG("1º Sargento"),
	_2SG("2º Sargento"),
	_3SG("3º Sargento"),
	CB("Cabo"),
	SD("Soldado"),
	MN("Marinheiro"),
	FC("Funcionário Civil"),
	SC("Servidor Civil");

	private String description = "";

	private PostGraduationEnum(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

}
