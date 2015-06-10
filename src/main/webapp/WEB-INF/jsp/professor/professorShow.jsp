<div class="container">
    <h2 class="text-success"><strong>${professor.nome}</strong></h2>
    <p><strong>Sexo: </strong> ${professor.sexo}</p>
    <p><strong>Nascimento: </strong> ${professor.getDataFormatada()}</p>
    <p><strong>Email: </strong> ${professor.email}</p>
    <p><strong>Telefone: </strong> ${professor.telefone}</p>
    <p><strong>Total de avaliações: </strong> ${professor.avaliacoes.size()}</p>

</div>
