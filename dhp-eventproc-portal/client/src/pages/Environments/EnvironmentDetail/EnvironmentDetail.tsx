import { useEffect } from "react";
import { useParams } from "react-router-dom";

const EnvironmentDetail = () => {
    let params = useParams();

    useEffect(() => {
        console.log(params)
    }, []);

    return <h1>Env Id: {params.environmentId}</h1>;
}

export default EnvironmentDetail;