
import {useOutletContext} from "react-router-dom";


const Description = () => {
    const { getAuction } = useOutletContext();
    return (
        <div className="description-container">  
            <h3>Description:</h3>              
            <pre>
                {getAuction?.items?.[0].itemDescription}          
            </pre>
        </div>
    );
}


export default Description;
