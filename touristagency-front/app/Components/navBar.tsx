import {Link} from "@nextui-org/react";

const NavBar = () => (
    <div>
      <Link href="/CarMarks" aria-current="page">
            CarMarks
      </Link>
      <Link href="/CarModels" aria-current="page">
            CarModels
      </Link>
      <Link href="/Cars" aria-current="page">
            Cars
      </Link>
      <Link href="/Transfers" aria-current="page">
            Transfers
      </Link>
    </div>
);

export default NavBar;