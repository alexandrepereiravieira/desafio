[completo] /register - [POST] - esta rota deve cadastrar um usuário;
[completo] /login - [POST] - esta rota deve autenticar um usuário;
[completo] /posts - [POST] - esta rota deve cadastrar uma postagem mantendo a referência do autor. (requer autenticação);
[completo] /posts/{id} - [PUT] - esta rota deve editar a postagem do ID especificado mantendo a referência do autor. (requer autenticação);
[incompleto] /posts - [GET] - esta rota deve retornar a lista de todas as postagens ordenadas das mais recentes para as mais antigas com a possibilidade de inverter esta ordenação e de retornar apenas as postagens do usuário que fez a requisição (requer autenticação);
    Obs.: não consegui ainda fazer o filtro por usuário que está requisitando
[completo] /posts/{id} - [GET] - esta rota deve retornar a postagem do ID especificado com todos os seus dados (requer autenticação);
[completo] /posts/{id} - [DELETE] - esta rota deve deletar a postagem do ID especificado.

