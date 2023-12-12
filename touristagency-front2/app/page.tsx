import {Providers} from "./providers";
import Image from 'next/image'
import React from "react";
import {Navbar, NavbarBrand, NavbarContent, NavbarItem, Link, Button} from "@nextui-org/react";
import {AcmeLogo} from "./AcmeLogo.tsx";
import {Input} from "@nextui-org/react";

export default function Home() {
  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-24">
    <Navbar>
          <NavbarBrand>
            <AcmeLogo />
            <p className="font-bold text-inherit">Туристическое агентство</p>
          </NavbarBrand>
          <NavbarContent className="hidden sm:flex gap-4" justify="center">
            <NavbarItem isActive>
              <Link href="/" aria-current="page" aria-current="page">
                Туры
              </Link>
            </NavbarItem>
            <NavbarItem>
              <Link color="foreground" href="/booking" aria-current="booking">
                Бронирования
              </Link>
            </NavbarItem>
            <NavbarItem>
              <Link color="foreground" href="/payments" aria-current="payments">
                Оплаты
              </Link>
            </NavbarItem>
            <NavbarItem>
              <Link color="foreground" href="/admin" aria-current="admin">
                Администрирование
              </Link>
            </NavbarItem>
          </NavbarContent>
          <NavbarContent justify="end">
            <NavbarItem className="hidden lg:flex">
              <Link href="#">Login</Link>
            </NavbarItem>
            <NavbarItem>
              <Button as={Link} color="primary" href="#" variant="flat">
                Sign Up
              </Button>
            </NavbarItem>
          </NavbarContent>
        </Navbar>
      <div>
      <Input type="email" label="Email" />
      <Input type="email" label="Email" placeholder="Enter your email" />

      </div>
      <div className="z-10 max-w-5xl w-full items-center justify-between font-mono text-sm lg:flex">
        <div className="fixed bottom-0 left-0 flex h-48 w-full items-end justify-center bg-gradient-to-t from-white via-white dark:from-black dark:via-black lg:static lg:h-auto lg:w-auto lg:bg-none">
          <Image
                        src="/palm.jpeg"
                        alt="Vercel Logo"
                        width={100}
                        height={24}
                        priority
                      />
        </div>
      </div>

      <div className="relative flex place-items-center before:absolute before:h-[300px] before:w-[480px] before:-translate-x-1/2 before:rounded-full before:bg-gradient-radial before:from-white before:to-transparent before:blur-2xl before:content-[''] after:absolute after:-z-20 after:h-[180px] after:w-[240px] after:translate-x-1/3 after:bg-gradient-conic after:from-sky-200 after:via-blue-200 after:blur-2xl after:content-[''] before:dark:bg-gradient-to-br before:dark:from-transparent before:dark:to-blue-700 before:dark:opacity-10 after:dark:from-sky-900 after:dark:via-[#0141ff] after:dark:opacity-40 before:lg:h-[360px] z-[-1]">

      </div>

      <div className="mb-32 grid text-center lg:max-w-5xl lg:w-full lg:mb-0 lg:grid-cols-4 lg:text-left">

      </div>
    </main>
  )
}
