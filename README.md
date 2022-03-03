# POS in Shell

- `cli`层负责用户交互，调用业务逻辑层的代码执行指令并打印信息
- `biz`层负责业务逻辑，实现各种指令的具体逻辑，检验输入数据并执行操作，返回结果给交互层
- `db`层负责数据访问读写，`InMemoryDB`将数据保存在内存中，可以通过实现接口来将数据保存到数据库或其他地方

具体实现（修改的指令）：

- `a`：添加商品到购物车，可以重复添加，重复添加会更新商品数量
- `c`：结账，打印价格总和并清空购物车
- `l`：打印所有的商品信息
- `m`：修改购物车某个商品的数量
- `n`：创建新的购物车（相当于清空购物车）
- `p`：打印购物车信息
- `r`：删除购物车某个商品

---

> The demo shows a simple POS system with command line interface. Currently it implements three commands which you can see using the `help` command.
>
> ```shell
>   .   ____          _            __ _ _
>  /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
> ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
>  \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
>   '  |____| .__|_| |_|_| |_\__, | / / / /
>  =========|_|==============|___/=/_/_/_/
>  :: Spring Boot ::                (v2.5.7)
>  
> shell:>help
> AVAILABLE COMMANDS
>
> Built-In Commands
>         clear: Clear the shell screen.
>         exit, quit: Exit the shell.
>         help: Display help about available commands.
>         history: Display or save the history of previously run commands
>         script: Read and execute commands from a file.
>         stacktrace: Display the full stacktrace of the last error.
>
> Pos Command
>         a: Add a Product to Cart
>         c: Checkout
>         l: List Products
>         m: Modify Item Amount in Cart
>         n: New Cart
>         p: Print Cart
>         r: Remove Item in Car
> ```
>
> Everytime a customer come to make a purchase, use `n` to create a new cart and then use `a ${productid} ${amount}` to add a product to the cart.
>
> Please make the POS system robust and fully functional by implementing more commands, for instance, print/empty/modify cart.
>
> Implementing a PosDB with real database is very much welcome. 
>
> Please elaborate your understanding in layered systems via this homework in your README.md.
