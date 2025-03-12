class ReadData {
    public static void listarUsuarios() {
        String sql = "SELECT * FROM usuario";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println("CPF: " + rs.getString("cpf") +
                        ", Nome: " + rs.getString("nome") +
                        ", Data de Nascimento: " + rs.getDate("dataNascimento") +
                        ", Dependentes: " + rs.getInt("dependentes") +
                        ", Escolaridade: " + rs.getInt("idEscolaridade") +
                        ", Estado Civil: " + rs.getInt("idEstadoCivil"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void listarEmpregos() {
        String sql = "SELECT * FROM emprego";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println("ID Emprego: " + rs.getInt("idEmprego") +
                        ", CPF: " + rs.getString("cpf") +
                        ", Salário Esperado: " + rs.getDouble("salarioEsperado") +
                        ", Data Início: " + rs.getDate("dataInicioEmprego") +
                        ", Data Fim: " + rs.getDate("dataFinalEmprego") +
                        ", Vínculo Trabalhista: " + rs.getInt("idVinculoTrabalista"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void listarHistoricoCredito() {
        String sql = "SELECT * FROM historicoCredito";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println("ID Crédito: " + rs.getInt("idCredito") +
                        ", CPF: " + rs.getString("cpf") +
                        ", Parcelas Restantes: " + rs.getInt("parcelasRestantes") +
                        ", Valor Parcela: " + rs.getDouble("valorParcela") +
                        ", Meses Atrasados: " + rs.getInt("mesesAtrasado") +
                        ", Está Inadimplente: " + rs.getBoolean("estaInadimplente") +
                        ", Valor Crédito Restante: " + rs.getDouble("valorCreditoRestante") +
                        ", Valor Crédito: " + rs.getDouble("valorCredito"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}