# Getting Started

## Dependencies

### Back-end

#### Generate DTOs and interfaces from OpenAPI specification
    brew install openapi-generator
    openapi-generator generate -g spring -o generated --api-package com.fdobrotv.touristagency.api --invoker-package com.fdobrotv.touristagency.invoker --model-package com.fdobrotv.touristagency.dto -i specs/touristagency-v1.0.yaml
    openapi-generator generate -g typescript-fetch -o touristagency-front/generated --api-package com.fdobrotv.touristagency.api --invoker-package com.fdobrotv.touristagency.invoker --model-package com.fdobrotv.touristagency.dto -i specs/touristagency-v1.0.yaml

#### Setup local development env
    brew install docker-compose
    brew install colima
    brew install libpq
    
    # delete existing instance!
    colima delete 
    colima start

### Front-end
    brew install npm yarn next
    npm i -g next
    npx create-next-app@latest
        Ok to proceed? (y) y
        ✔ What is your project named? … touristagency-front
        ✔ Would you like to use TypeScript? … No / Yes
        ✔ Would you like to use ESLint? … No / Yes
        ✔ Would you like to use Tailwind CSS? … No / Yes
        ✔ Would you like to use `src/` directory? … No / Yes
        ✔ Would you like to use App Router? (recommended) … No / Yes
        ✔ Would you like to customize the default import alias (@/*)? … No / Yes
        
    
### Environment
    docker-compose up

## Development run

### Front-end
    npm run dev
    curl -v http://localhost:3000
